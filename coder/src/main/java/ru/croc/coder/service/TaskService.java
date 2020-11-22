package ru.croc.coder.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import ru.croc.coder.controller.dto.DecisionDto;
import ru.croc.coder.domain.tasks.*;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.DecisionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TaskService {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private DecisionRepository decisionRepository;

    private UserContext userContext;

    private CourseRepository courseRepository;

    public TaskService(UserRepository userRepository, TaskRepository taskRepository,
                       DecisionRepository decisionRepository, UserContext userContext,
                       CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.decisionRepository = decisionRepository;
        this.userContext = userContext;
        this.courseRepository = courseRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ProblemConstraintException.class)
    public Decision submit(Long taskId, String code) {
        User user = userContext.getCurrentUser();
        if ((user instanceof Student) && user.getCourse() == null) {
            throw new StudentDoesNotEnrollException("Student haven't enrolled to the course");
        }

        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundException::new);
        if (!user.getCourse().getId().equals(task.getCourse().getId())) {
            throw new PermissionDenied("You cannot submit decision from another course");
        }
        userRepository.save(user.setAttemptsCount(user.getAttemptsCount() + 1));

        if (task.getMaxAttempts() != null) {
            long attempts = decisionRepository.countByAuthorAndTask(user, task);
            if (attempts >= task.getMaxAttempts()) {
                throw new ProblemConstraintException("Max attempts exceeded");
            }
        }

        Decision decision = new Decision()
                .setAuthor(user)
                .setSubmitTime(LocalDateTime.now())
                .setTask(task)
                .setCode(new Code()
                                .setLanguage(ProgrammingLanguage.JAVA)
                                .setText(code))
                .setCheckStatus(ProcessStatus.QUEUED);

        LocalDateTime timeOfSubmit = decision.getSubmitTime();

        if (task.getTimeOfStart().isAfter(timeOfSubmit)) {
            throw new TaskHasNotStartedException("Task didn't start! The task will start "
                    + task.getTimeOfStart().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
        if (task.getTimeToDeadLine().isBefore(timeOfSubmit)) {
            throw new TimeEndedException("Time to solve this task is up");
        }
        return decisionRepository.save(decision);
    }

    @Async
    @Scheduled(fixedRate = 1_000, initialDelay = 3_000)
    public void checkDecision() throws InterruptedException {
        log.info("Scheduled check");

        Decision decision = null;
        try {
            Optional<Decision> result = peekNextDecision();
            if (result.isEmpty())
                return;
            decision = result.get();
            boolean solved = runTests(decision);
            if (solved) {
                User user = decision.getAuthor();
                user.setPoints(user.getPoints() + 10);
                userRepository.save(user);
            }
            decision.setSolved(solved);
        } finally {
            if (decision != null) {
                decision.setCheckStatus(ProcessStatus.COMPLETED);
                decisionRepository.save(decision);
            }
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Optional<Decision> peekNextDecision() {
        //find QUEUED
        Optional<Decision> result = decisionRepository.findAnyQueued();
        if (result.isPresent()) {
            //set IN_PROGRESS
            Decision decision = result.get();
            decision.setCheckStatus(ProcessStatus.IN_PROGRESS);
            decisionRepository.save(decision);
        }
        return result;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Task createTask(Long courseId, Task task) {
        if (userContext.getCurrentUser() == null) {
            throw new NotAuthorizedException("It needs authentication");
        }
        if (userContext.getCurrentUser() instanceof Student) {
            throw new PermissionDenied("You cannot create task");
        }
        Teacher currentTeacher = (Teacher) userContext.getCurrentUser();
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        task.setCourse(course).setAuthor(currentTeacher);
        taskRepository.save(task);
        return task;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<DecisionDto> getDecisionsStat() {
        User user = userContext.getCurrentUser();
        if (user == null) {
            throw new NotAuthorizedException("You need to authenticate!");
        }
        List<Decision> decisionsByAuthor = decisionRepository.findDecisionsByAuthorId(user.getId());
        if (decisionsByAuthor.isEmpty()) {
            throw new DecisionsDontExistException("You don't have any decision");
        }
        ModelMapper modelMapper = new ModelMapper();
        List<DecisionDto> decisionStatByAuthor = new ArrayList<>();
        for (var decision : decisionsByAuthor) {
            decisionStatByAuthor.add(modelMapper.map(decision, DecisionDto.class));
        }
        return decisionStatByAuthor;
    }

    private boolean runTests(Decision decision) throws InterruptedException {
        Thread.sleep(5_000);
        return random.nextBoolean();
    }
}

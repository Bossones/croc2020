package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.tasks.*;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.DecisionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.ProblemConstraintException;
import ru.croc.coder.service.exceptions.TimeEndedException;

import java.time.LocalDateTime;
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

    public TaskService(UserRepository userRepository, TaskRepository taskRepository,
                       DecisionRepository decisionRepository, UserContext userContext) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.decisionRepository = decisionRepository;
        this.userContext = userContext;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = ProblemConstraintException.class)
    public Decision submit(Long taskId, String code) {
        User user = userContext.getCurrentUser();
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundException::new);

        userRepository.save(user.setAttemptsCount(user.getAttemptsCount() + 1));

        if (task.getMaxAttempts() != null) {
            long attempts = decisionRepository.countByAuthorAndTask(user, task);
            if (attempts >= task.getMaxAttempts()) {
                throw new ProblemConstraintException("Max attempts exceeded");
            }
        }

        Decision decision = new Decision()
                .setAuthor(user)
                .setTime(LocalDateTime.now())
                .setTask(task)
                .setCode(new Code()
                                .setLanguage(ProgrammingLanguage.JAVA)
                                .setText(code))
                .setCheckStatus(ProcessStatus.QUEUED);

        if (task.getTimeToDeadLine().isBefore(decision.getTime()))
            throw new TimeEndedException("Time to solve this task is up");
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

    private boolean runTests(Decision decision) throws InterruptedException {
        Thread.sleep(5_000);
        return random.nextBoolean();
    }
}

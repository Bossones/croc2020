package ru.croc.coder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.coder.domain.tasks.Code;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.tasks.ProgrammingLanguage;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.DecisionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.ProblemConstraintException;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class TaskService {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DecisionRepository decisionRepository;

    public Decision submit(Long userId, Long taskId, String code) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        Task task = taskRepository.findById(taskId).orElseThrow(NotFoundException::new);
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
                .setSolved(random.nextBoolean());
        return decisionRepository.save(decision);
    }
}

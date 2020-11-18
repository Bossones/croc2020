package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.tasks.*;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.TaskService;

import java.time.LocalDateTime;

@Component
public class Init implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @Override
    public void run(String... args) {
        log.info("Init application");

        /*Teacher teacher = createTeacher("Andrei", "Kogun", "akogun@croc.ru", "1234");
        Student student = createStudent("Bogdan", "Boginskiy", "b.boginskiy@mail.ru", "0234123");
        Task task = createEasyJavaTask(
                3,
                "Test Description",
                LocalDateTime.now().plusDays(4),
                new Code().setText("Some text").setLanguage(ProgrammingLanguage.JAVA),
                teacher);
        userRepository.save(teacher);
        userRepository.save(student);
        taskRepository.save(task);*/
        /*taskService.submit(student.getId(), task.getId(), "Hello World");
        taskService.submit(student.getId(), task.getId(), "Hello World2");
        taskService.submit(student.getId(), task.getId(), "Hello World3");*/
        //taskService.submit(student.getId(), task.getId(), "Hello World4");
    }

    private User createUser(String name, String lastName, String email, String password) {
        return new User()
                .setEmail(email)
                .setFirstName(name)
                .setLastName(lastName)
                .setPassword(password);
    }

    private Teacher createTeacher(String name, String lastName, String email, String password) {
        return new Teacher()
                .setEmail(email)
                .setFirstName(name)
                .setLastName(lastName)
                .setPassword(password);
    }

    private Task createEasyJavaTask(Integer maxAttempts, String description, LocalDateTime deadLine, Code template, Teacher author) {
        return new Task()
                .setAuthor(author)
                .setDescription(description)
                .setLevel(TaskLevel.EASY)
                .setMaxAttempts(maxAttempts)
                .setTimeToDeadLine(deadLine)
                .setTemplate(template);
    }

    private Student createStudent(String name, String lastName, String email, String password) {
        return new Student()
                .setEmail(email)
                .setFirstName(name)
                .setLastName(lastName)
                .setPassword(password);
    }
}

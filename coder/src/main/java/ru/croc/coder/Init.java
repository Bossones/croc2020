package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.DecisionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;

@Component
public class Init implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private DecisionRepository decisionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Init application");

        long numUser = userRepository.count();
        log.info("Number of users: {}", numUser);

        Teacher teacher = new Teacher()
                .setFirstName("Konstantin")
                .setLastName("Vasilievich")
                .setEmail("k.vasilievich@gmail.com")
                .setProfile("Machine Learning");

        Course course = new Course()
                .setNameOfCourse("Machine Learning");

        Task task = new Task()
                .setNameOfTask("task n 001");

        Student student = new Student()
                .setFirstName("Svetlana")
                .setLastName("Krasnova")
                .setEmail("s.krasnova@mail.ru");

        Decision decision = new Decision()
                .setSolved(true)
                .setContent("public static void main(String[] args) { System.out.println(\"Hello world!\")}");

        User randomUser = createUser("Bogdan", "Boginskiy", "bogdan_boginskiy@mail.ru");
        User randomUser2 = createUser("Nikolai", "Dexter", "n.dexter@croc.ru");

        log.info("saving student");
        userRepository.save(student);
        log.info("saving teacher");
        userRepository.save(teacher);
        log.info("saving task");
        taskRepository.save(task);
        log.info("saving course");
        courseRepository.save(course);
        decisionRepository.save(decision);

        userRepository.save(randomUser);
        userRepository.save(randomUser2);

//        if (userRepository.findByEmailIgnoreCase("bogdan_boginskiy@mail.ru").isEmpty()) {
//            log.info("Creating initial user");
//            Teacher teacher = new Teacher()
//                    .setEmail("bogdan_boginskiy@mail.ru")
//                    .setFirstName("Bogdan")
//                    .setLastName("Boginskiy");
//            userRepository.save(teacher);
//        }
    }

    private User createUser(String name, String lastName, String email) {
        return new User()
                .setEmail(email)
                .setFirstName(name)
                .setLastName(lastName);
    }
}

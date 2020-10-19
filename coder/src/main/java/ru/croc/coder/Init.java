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
                .setEmail("ilya_mamaev@google.com")
                .setFirstName("Ilya")
                .setLastName("Mamaev")
                .setProfile("Machine Learning");

        Student student = new Student()
                .setEmail("bogdan_boginskiy@mail.ru")
                .setFirstName("Bogdan")
                .setLastName("Boginskiy");

        Course courseMachineLearning = new Course()
                .setOpened(true)
                .setNameOfCourse("Machine Learning");

        Task task = new Task()
                .setNameOfTask("Task one");

        Decision decision = new Decision()
                .setContent("public static void main(String[] args) { \n System.out.println(\"Hello world\")\n}")
                .setSolved(true)
                .setStudent(student);


        teacher.setCourse(courseMachineLearning);
        courseMachineLearning.setTask(task);
        courseMachineLearning.setStudent(student);
        student.setCoursesForStudent(courseMachineLearning);
        task.setCourse(courseMachineLearning);
        task.setDecisionOfTask(decision);

        userRepository.save(teacher);
        userRepository.save(student);
        courseRepository.save(courseMachineLearning);
        taskRepository.save(task);
        decisionRepository.save(decision);



//        if (userRepository.findByEmailIgnoreCase("bogdan_boginskiy@mail.ru").isEmpty()) {
//            log.info("Creating initial user");
//            Teacher teacher = new Teacher()
//                    .setEmail("bogdan_boginskiy@mail.ru")
//                    .setFirstName("Bogdan")
//                    .setLastName("Boginskiy");
//            userRepository.save(teacher);
//        }
    }
}

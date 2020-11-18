package ru.croc.coder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.CourseCreatingPermissionDenied;

import java.time.LocalDateTime;

@Service
public class CourseService {

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    private CourseRepository courseRepository;

    private ObjectMapper objectMapper;

    private UserContext userContext;

    public CourseService(TaskRepository taskRepository, UserRepository userRepository, ObjectMapper objectMapper,
                         UserContext userContext, CourseRepository courseRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userContext = userContext;
        this.courseRepository = courseRepository;
    }

    //@Transactional(isolation = Isolation.READ_COMMITTED)
    public Course courseRegistration(String jsonCourseInformation) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        Course course = objectMapper.readValue(jsonCourseInformation, Course.class);
        if (userContext.getCurrentUser() instanceof Student) {
            throw new CourseCreatingPermissionDenied("You cannot create course");
        }
        Teacher currentTeacher = (Teacher) userContext.getCurrentUser();
        course.setRegistrationTime(LocalDateTime.now()).setOneTeacher(currentTeacher);
        currentTeacher.setOneCourse(course);
        courseRepository.save(course);
        userRepository.save(currentTeacher);
        return course;
    }
}
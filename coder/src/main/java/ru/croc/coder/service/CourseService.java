package ru.croc.coder.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.controller.dto.CourseDto;
import ru.croc.coder.controller.dto.CourseStatDto;
import ru.croc.coder.controller.dto.StudentCourseStatDto;
import ru.croc.coder.controller.dto.TaskDto;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;
import ru.croc.coder.repository.CourseRepository;
import ru.croc.coder.repository.DecisionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;
import ru.croc.coder.service.exceptions.NotAuthorizedException;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.PermissionDenied;
import ru.croc.coder.service.exceptions.UserIsBusyException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private TaskRepository taskRepository;

    private UserRepository userRepository;

    private CourseRepository courseRepository;

    private DecisionRepository decisionRepository;

    private ObjectMapper objectMapper;

    private UserContext userContext;

    public CourseService(TaskRepository taskRepository, UserRepository userRepository, ObjectMapper objectMapper,
                         UserContext userContext, CourseRepository courseRepository,
                         DecisionRepository decisionRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
        this.userContext = userContext;
        this.courseRepository = courseRepository;
        this.decisionRepository = decisionRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Course courseRegistration(String jsonCourseInformation) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        if (userContext.getCurrentUser() == null) {
            throw new NotAuthorizedException("It needs to authenticate");
        }
        if (userContext.getCurrentUser() instanceof Student) {
            throw new PermissionDenied("You cannot create course");
        }
        Teacher currentTeacher = (Teacher) userContext.getCurrentUser();
        if (currentTeacher.getCourse() != null) {
            throw new UserIsBusyException("You are already working on different course");
        }
        Course course = objectMapper.readValue(jsonCourseInformation, Course.class);
        course.setCreationTime(LocalDateTime.now());
        currentTeacher.setCourse(course);
        courseRepository.save(course);
        userRepository.save(currentTeacher);
        return course;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<TaskDto> getCourseTasks(Long courseId) {
        ModelMapper modelMapper = new ModelMapper();
        List<Task> tasks = taskRepository.findTasksByCourseId(courseId);
        List<TaskDto> tasksDto = new ArrayList<>();
        for (var task : tasks) {
            tasksDto.add(modelMapper.map(task, TaskDto.class));
        }
        return tasksDto;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Student enrollStudent(Long courseId, Long studentId) {
        User user = userContext.getCurrentUser();
        if (user instanceof Student) {
            throw new PermissionDenied("You cannot enroll student! You ain't a teacher!");
        }
        if (user == null) {
            throw new NotAuthorizedException("You need authorize to perform that");
        }
        Student student = userRepository.findStudentById(studentId).orElseThrow(NotFoundException::new);
        if (student.getCourse() != null) {
            throw new UserIsBusyException("Student is busy on a different course");
        }
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        student.setCourse(course);
        userRepository.save(student);
        return student;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public CourseStatDto courseStat(Long courseId) {
        User user = userContext.getCurrentUser();
        if (user instanceof Student) {
            throw new PermissionDenied("You cannot do this");
        }
        Course course = courseRepository.findById(courseId).orElseThrow(NotFoundException::new);
        List<Student> studentsInCourse = userRepository.findStudentsByCourseId(courseId);
        List<Task> tasksInCourse = taskRepository.findTasksByCourseId(courseId);

        Long countSolved = 0L;
        Long countNonSolved = 0L;
        for (var task : tasksInCourse) {
            countSolved += decisionRepository.countBySolvedAndTask(true, task);
            countNonSolved += decisionRepository.countBySolvedAndTask(false, task);
        }

        ModelMapper modelMapper = new ModelMapper();
        CourseStatDto courseStatDto = new CourseStatDto();
        List<StudentCourseStatDto> studentCourseStat = new ArrayList<>();
        List<TaskDto> tasksCourseStat = new ArrayList<>();

        for (var student : studentsInCourse) {
            studentCourseStat.add(modelMapper.map(student, StudentCourseStatDto.class));
        }
        for (var task : tasksInCourse) {
            tasksCourseStat.add(modelMapper.map(task, TaskDto.class));
        }

        courseStatDto.setCourseDto(modelMapper.map(course, CourseDto.class));
        courseStatDto.setStudentsInCourse(studentCourseStat);
        courseStatDto.setTaskInCourse(tasksCourseStat);
        courseStatDto.setSolvedDecisions(countSolved);
        courseStatDto.setNonSolvedDecisions(countNonSolved);
        return courseStatDto;
    }
}

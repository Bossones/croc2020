package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.croc.coder.controller.dto.CourseDto;
import ru.croc.coder.controller.dto.CourseStatDto;
import ru.croc.coder.controller.dto.TaskDto;
import ru.croc.coder.controller.dto.UserDto;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.service.CourseService;

import java.util.List;

@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/register/course")
    public CourseDto courseRegistration(@RequestBody String jsonCourseInformation) throws JsonProcessingException {
        Course course = courseService.courseRegistration(jsonCourseInformation);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(course, CourseDto.class);
    }

    @GetMapping("/course/{courseId}/tasks")
    public List<TaskDto> getCourseTasks(@PathVariable Long courseId) {
        return courseService.getCourseTasks(courseId);
    }

    @PostMapping("/course/{courseId}/enroll_student/{studentId}")
    public UserDto enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(courseService.enrollStudent(courseId, studentId), UserDto.class);
    }

    @GetMapping("/course_stat/{courseId}")
    public CourseStatDto getCourseStat(@PathVariable Long courseId) {
        return courseService.courseStat(courseId);
    }

    @PostMapping("/course/expulsion/{studentId}")
    public UserDto expulsionFromCourse(@PathVariable Long studentId) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(courseService.expulsionFromCourse(studentId), UserDto.class);
    }
}

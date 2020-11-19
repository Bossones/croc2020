package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
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
}

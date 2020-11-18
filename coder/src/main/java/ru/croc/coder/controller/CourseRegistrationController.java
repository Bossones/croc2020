package ru.croc.coder.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.service.CourseService;

@RestController
public class CourseRegistrationController {

    private CourseService courseService;

    public CourseRegistrationController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/register/course")
    public CourseDto courseRegistration(@RequestBody String jsonCourseInformation) throws JsonProcessingException {
        Course course = courseService.courseRegistration(jsonCourseInformation);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(course, CourseDto.class);
    }
}

package ru.croc.coder.controller;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDateTime;

public class CourseDto {

    private String courseName;

    private String courseDescription;

    private LocalDateTime registrationTime;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }
}

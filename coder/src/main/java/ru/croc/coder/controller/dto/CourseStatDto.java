package ru.croc.coder.controller.dto;

import java.util.List;

public class CourseStatDto {

    private CourseDto courseDto;

    private List<StudentCourseStatDto> studentsInCourse;

    private Long solvedDecisions;

    private Long nonSolvedDecisions;

    private List<TaskDto> tasksInCourse;

    public CourseDto getCourseDto() {
        return courseDto;
    }

    public void setCourseDto(CourseDto courseDto) {
        this.courseDto = courseDto;
    }

    public List<StudentCourseStatDto> getStudentsInCourse() {
        return studentsInCourse;
    }

    public void setStudentsInCourse(List<StudentCourseStatDto> studentsInCourse) {
        this.studentsInCourse = studentsInCourse;
    }

    public Long getSolvedDecisions() {
        return solvedDecisions;
    }

    public void setSolvedDecisions(Long solvedDecisions) {
        this.solvedDecisions = solvedDecisions;
    }

    public Long getNonSolvedDecisions() {
        return nonSolvedDecisions;
    }

    public void setNonSolvedDecisions(Long nonSolvedDecisions) {
        this.nonSolvedDecisions = nonSolvedDecisions;
    }

    public List<TaskDto> getTaskInCourse() {
        return tasksInCourse;
    }

    public void setTaskInCourse(List<TaskDto> taskInCourse) {
        this.tasksInCourse = taskInCourse;
    }
}

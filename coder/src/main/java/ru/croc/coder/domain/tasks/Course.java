package ru.croc.coder.domain.tasks;

import org.hibernate.annotations.Proxy;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Course")
@Table(name = "COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Lob
    @Column(name = "courseDescription")
    private String courseDescription;

    @Column(name = "creationTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime creationTime;

    @Column(name = "timeOfPublication", columnDefinition = "TIMESTAMP")
    private LocalDateTime timeOfPublication;

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getTimeOfPublication() {
        return timeOfPublication;
    }

    public Course setTimeOfPublication(LocalDateTime timeOfPublication) {
        this.timeOfPublication = timeOfPublication;
        return this;
    }

    public Course setCreationTime(LocalDateTime registrationTime) {
        this.creationTime = registrationTime;
        return this;
    }

    public Course setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }

    public Course setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
        return this;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getId() {
        return id;
    }
}

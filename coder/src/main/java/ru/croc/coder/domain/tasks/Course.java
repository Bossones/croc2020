package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @ManyToMany(mappedBy = "courses")
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    @Column(name = "registrationTime", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime registrationTime;

    @OneToMany(mappedBy = "course")
    private Set<Task> tasks = new HashSet<>();

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public Course setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
        return this;
    }

    public Course setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Course setOneTask(Task task) {
        tasks.add(task);
        return this;
    }

    public Course setOneStudent(Student student) {
        students.add(student);
        return this;
    }

    public Course setOneTeacher(Teacher teacher) {
        teachers.add(teacher);
        return this;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Course setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Course setStudents(Set<Student> students) {
        this.students = students;
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

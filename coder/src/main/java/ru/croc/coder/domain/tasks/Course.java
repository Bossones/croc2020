package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nameOfCourse;

    @Column(nullable = false)
    private boolean opened;

    @OneToMany
    private List<Task> tasks;

    @ManyToMany
    private List<Teacher> teachers;

    @ManyToMany
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public Course setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public Course setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
        return this;
    }

    public List<Task> getTasks() throws IllegalAccessException {
        if(opened) return tasks;
        else throw new IllegalAccessException("You cannot get access to this course");
    }

    public List<Task> getTasksForStudent(Student student) throws IllegalAccessException {
        Objects.requireNonNull(student);
        if(students.contains(student)) return tasks;
        else throw new IllegalAccessException("You cannot get access to this course");
    }

    public boolean setTask(Task task) {
        Objects.requireNonNull(task);
        if (!tasks.contains(task))
            return tasks.add(task);
        return false;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Course setStudent(Student student) {
        Objects.requireNonNull(student);
        if (!students.contains(student))
            students.add(student);
        return this;
    }

    public boolean isOpened() {
        return opened;
    }

    public Course setTeacher(Teacher teacher) {
        Objects.requireNonNull(teacher);
        if (!teachers.contains(teacher))
            this.teachers.add(teacher);
        return this;
    }

    public Course setOpened(boolean opened) {
        this.opened = opened;
        return this;
    }
}

package ru.croc.coder.domain.tasks;

import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Table(name = "Course")
@Entity(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nameOfCourse;

    @Column(nullable = false)
    private boolean opened;

/*    @OneToMany
    private Set<Task> tasks;

    @ManyToMany
    private Set<Teacher> teachers;

    @ManyToMany
    private Set<Student> students;*/

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
        Objects.requireNonNull(nameOfCourse);
        this.nameOfCourse = nameOfCourse;
        return this;
    }
    /*

    public Set<Task> getTasks() throws IllegalAccessException {
        if(opened) return tasks;
        else throw new IllegalAccessException("You cannot get access to this course");
    }

    public Set<Task> getTasksForStudent() {
        return tasks;
    }

    public boolean setTask(Task task) {
        Objects.requireNonNull(task);
        return tasks.add(task);
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Course setStudent(Student student) {
        Objects.requireNonNull(student);
        students.add(student);
        return this;
    }
*/

    public boolean isOpened() {
        return opened;
    }

/*    public Course setTeacher(Teacher teacher) {
        Objects.requireNonNull(teacher);
        teachers.add(teacher);
        return this;
    }*/

    public Course setOpened(boolean opened) {
        this.opened = opened;
        return this;
    }
}

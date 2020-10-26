package ru.croc.coder.domain.users;

import ru.croc.coder.domain.tasks.Course;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Student extends User {

/*    @ManyToMany
    private Set<Course> coursesForStudent;*/

/*    public Set<Course> getCoursesForStudent() {
        return coursesForStudent;
    }

    public Student setCoursesForStudent(Course course) {
        Objects.requireNonNull(course);
        coursesForStudent.add(course);
        return this;
    }*/

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Student setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public Student setEmail(String email) {
        super.setEmail(email);
        return this;
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public Student setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public Student setLastName(String lastName) {
        super.setLastName(lastName);
        return this;
    }
}

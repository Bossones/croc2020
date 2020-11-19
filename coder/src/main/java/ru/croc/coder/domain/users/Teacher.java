package ru.croc.coder.domain.users;

import ru.croc.coder.domain.tasks.Course;

import javax.persistence.*;

@Table(name = "TEACHERS")
@Entity(name = "Teacher")
public class Teacher extends User {

    @Override
    public Teacher setCourse(Course course) {
        super.setCourse(course);
        return this;
    }

    @Override
    public Teacher setAttemptsCount(Integer attemptsCount) {
        super.setAttemptsCount(attemptsCount);
        return this;
    }

    @Override
    public Teacher setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public Teacher setEmail(String email) {
        super.setEmail(email);
        return this;
    }

    @Override
    public Teacher setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    @Override
    public Teacher setLastName(String lastName) {
        super.setLastName(lastName);
        return this;
    }

    @Override
    public Teacher setPassword(String password) {
        super.setPassword(password);
        return this;
    }
}

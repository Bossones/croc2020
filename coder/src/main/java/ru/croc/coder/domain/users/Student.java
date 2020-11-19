package ru.croc.coder.domain.users;

import ru.croc.coder.domain.tasks.Course;

import javax.persistence.*;

@Entity(name = "Student")
@Table(name = "STUDENTS")
public class Student extends User {
    @Override
    public Student setCourse(Course course) {
        super.setCourse(course);
        return this;
    }

    @Override
    public Student setAttemptsCount(Integer attemptsCount) {
        super.setAttemptsCount(attemptsCount);
        return this;
    }

    @Override
    public Student setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public Student setEmail(String email) {
        super.setEmail(email);
        return this;
    }

    @Override
    public Student setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    @Override
    public Student setLastName(String lastName) {
        super.setLastName(lastName);
        return this;
    }

    @Override
    public Student setPassword(String password) {
        super.setPassword(password);
        return this;
    }
}

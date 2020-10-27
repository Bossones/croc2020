package ru.croc.coder.domain.users;

import ru.croc.coder.domain.tasks.Course;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Teacher extends User {

    private String profile;
/*
    @ManyToMany(mappedBy = "")
    private Set<Course> courses;*/

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public Teacher setId(Long id) {
        super.setId(id);
        return this;
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public Teacher setEmail(String email) {
        super.setEmail(email);
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public Teacher setProfile(String profile) {
        Objects.requireNonNull(profile);
        this.profile = profile;
        return this;
    }

   /* public Set<Course> getCourses() {
        return courses;
    }

    public Teacher setCourse(Course course) {
        Objects.requireNonNull(course);
        courses.add(course);
        return this;
    }*/

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public Teacher setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public Teacher setLastName(String lastName) {
        super.setLastName(lastName);
        return this;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public Teacher setPassword(String password) {
        super.setPassword(password);
        return this;
    }
}

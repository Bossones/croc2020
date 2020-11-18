package ru.croc.coder.domain.users;

import javax.persistence.*;

@Entity
public class Student extends User {

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

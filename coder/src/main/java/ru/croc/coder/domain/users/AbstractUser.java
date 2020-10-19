package ru.croc.coder.domain.users;

import javax.persistence.*;

@Entity
public abstract class AbstractUser implements User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public AbstractUser setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public AbstractUser setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public AbstractUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public AbstractUser setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}

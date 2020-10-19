package ru.croc.coder.domain.users;

public interface User {

    Long getId();

    User setId(Long id);

    String getEmail();

    User setEmail(String email);

    String getFirstName();

    User setFirstName(String firstName);

    String getLastName();

    User setLastName(String lastName);
}

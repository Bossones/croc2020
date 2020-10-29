package ru.croc.coder.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.users.User;


import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

}

package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.users.AbstractUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AbstractUser, Long> {

    Optional<AbstractUser> findByEmailIgnoreCase(String email);

}

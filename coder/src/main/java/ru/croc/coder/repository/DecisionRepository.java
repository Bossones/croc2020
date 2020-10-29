package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;

import java.util.Optional;

public interface DecisionRepository extends CrudRepository<Decision, Long> {

    long countByAuthorAndTask(User author, Task task);

    @Override
    @RestResource(exported = false)
    <S extends Decision> S save(S entity);
}

package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.tasks.ProcessStatus;
import ru.croc.coder.domain.tasks.Task;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.Teacher;
import ru.croc.coder.domain.users.User;

import java.util.Optional;

public interface DecisionRepository extends CrudRepository<Decision, Long> {

    @Transactional
    Optional<Decision> findTopByCheckStatus(ProcessStatus processStatus);

    default Optional<Decision> findAnyQueued() {
        return findTopByCheckStatus(ProcessStatus.QUEUED);
    }

    long countByAuthorAndTask(User author, Task task);

    @Override
    @RestResource(exported = false)
    <S extends Decision> S save(S entity);
}

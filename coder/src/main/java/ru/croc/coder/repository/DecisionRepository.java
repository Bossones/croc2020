package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Decision;
import ru.croc.coder.domain.users.Student;

import java.util.Optional;

public interface DecisionRepository extends CrudRepository<Decision, Long> {

    Optional<Student> findByStudent(Student student);
}

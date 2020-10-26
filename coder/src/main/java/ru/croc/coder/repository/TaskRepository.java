package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.domain.tasks.Task;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {


}

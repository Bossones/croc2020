package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {


}

package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Course;

import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Override
    Optional<Course> findById(Long aLong);
}

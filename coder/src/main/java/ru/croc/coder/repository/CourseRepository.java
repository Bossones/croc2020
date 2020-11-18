package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}

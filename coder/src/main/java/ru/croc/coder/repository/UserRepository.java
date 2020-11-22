package ru.croc.coder.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.tasks.Course;
import ru.croc.coder.domain.users.Student;
import ru.croc.coder.domain.users.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    Optional<Student> findStudentById(Long studentId);

    List<Student> findStudentsByCourseId(Long courseId);
}

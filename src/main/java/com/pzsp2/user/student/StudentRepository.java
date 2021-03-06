package com.pzsp2.user.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Override
    List<Student> findAll();

    Optional<Student> findByUserFirstNameIgnoreCaseAndUserLastNameIgnoreCaseAndIdNumberIgnoreCase(
            String firstName, String lastName, String id);
}

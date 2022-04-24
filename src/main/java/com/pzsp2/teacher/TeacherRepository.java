package com.pzsp2.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();

    Teacher findTeacherByUserUserId(Long id);
}

package com.pzsp2.user.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();

    Teacher getTeacherByUserUserId(Long id);

    Boolean existsByUserUserId(Long teacherId);
}

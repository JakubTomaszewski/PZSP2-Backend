package com.pzsp2.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    @Override
    List<Course> findAll();

    @Override
    Optional<Course> findById(String s);

    Course findCourseByCourseCode(String courseCode);

    Boolean existsCourseByCourseCode(String courseCode);
}

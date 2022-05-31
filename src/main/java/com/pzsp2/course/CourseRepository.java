package com.pzsp2.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    @Override
    List<Course> findAll();

    Course findCourseByCourseCode(String courseCode);

    Boolean existsCourseByCourseCode(String courseCode);
}

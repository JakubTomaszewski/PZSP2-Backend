package com.pzsp2.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
  Course findCourseByCourseCode(String courseCode);

  Boolean existsCourseByCourseCode(String courseCode);
}

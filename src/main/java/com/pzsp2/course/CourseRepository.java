package com.pzsp2.course;

import com.pzsp2.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByCourseCode(String courseCode);
}

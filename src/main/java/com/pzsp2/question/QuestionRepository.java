package com.pzsp2.question;

import com.pzsp2.course.Course;
import com.pzsp2.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    List<Question> findAll();

    List<Question> findQuestionsByCourseCourseCode(String code);

    List<Question> findQuestionsByCourseName(String name);

    List<Question> findQuestionsByTypeEqualsIgnoreCase(String type);
}

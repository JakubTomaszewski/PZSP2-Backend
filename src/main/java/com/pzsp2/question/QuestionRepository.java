package com.pzsp2.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Override
    List<Question> findAll();

    List<Question> getQuestionsByCourseCourseCode(String code);

    List<Question> getQuestionsByCourseName(String name);

    List<Question> getQuestionsByTypeEqualsIgnoreCase(String type);

    List<Question> findByQuestionIdIn(List<Long> ids);

    List<Question> getQuestionsByTeachersLogin(String login);

    int countQuestionsByQuestionIdIn(List<Long> ids);


}

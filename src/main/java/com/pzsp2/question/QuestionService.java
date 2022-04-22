package com.pzsp2.question;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestionsByCourseCode(String courseCode) {
        return questionRepository.findQuestionsByCourseCourseCode(courseCode);
    }

    public List<Question> getAllQuestionsByCourseName(String courseName) {
        return questionRepository.findQuestionsByCourseName(courseName);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question addQuestion(Question question) {
        if (question == null) {
            throw new ResourceNotFoundException();
        } else {
            return questionRepository.save(question);
        }
    }
}

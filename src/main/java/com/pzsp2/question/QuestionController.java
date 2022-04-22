package com.pzsp2.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<Question> displayQuestionsByCourseCode() {
        return questionService.getAll();
    }

    @GetMapping(path = "/course")
    public List<Question> displayQuestionsByCourseCode(@RequestParam(value = "name") String name) {
        return questionService.getAllQuestionsByCourseName(name);
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        question.setDateAdded(sqlDate);
        return questionService.addQuestion(question);
    }


}

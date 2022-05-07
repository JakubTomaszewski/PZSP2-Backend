package com.pzsp2.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping(path = "/all")
    public List<Question> displayAllQuestions() { return questionService.getAll(); }

    @GetMapping(path = "/all/closed")
    public List<Question> displayAllClosedQuestions() { return questionService.getAllClosed(); }

    @GetMapping(path = "/course")
    public List<Question> displayQuestionsByCourseCode(@RequestParam(value = "name") String name) {
        return questionService.getAllQuestionsByCourseName(name);
    }

    @PostMapping(path="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Question saveQuestion(@RequestBody AddQuestionRequest request) {
        return questionService.addQuestion(request);
    }
}

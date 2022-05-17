package com.pzsp2.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {

  @Autowired QuestionService questionService;

  @GetMapping(path = "/all")
  public List<Question> displayAllQuestions() {
    return questionService.getAll();
  }

  @GetMapping(path = "/all/closed")
  public List<Question> displayAllClosedQuestions() {
    return questionService.getAllClosed();
  }

  @GetMapping(path = "/course/name")
  public List<Question> displayQuestionsByCourseName(@RequestParam(value = "name") String name) {
    return questionService.getAllQuestionsByCourseName(name);
  }

  @GetMapping(path = "/course/code")
  public List<Question> displayQuestionsByCourseCode(@RequestParam(value = "code") String code) {
    return questionService.getAllQuestionsByCourseCode(code);
  }

  @GetMapping(path = "/teacher")
  public List<Question> displayQuestionsByTeacherLogin(
      @RequestParam(value = "login") String login) {
    return questionService.getAllQuestionsByTeacherLogin(login);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Question saveQuestion(@RequestBody QuestionRequest request) {
    return questionService.addQuestion(request);
  }

  @DeleteMapping()
  public Question deleteQuestionById(@RequestParam(value = "id") Long id) {
    return questionService.deleteQuestionById(id);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public Question updateQuestion(@RequestBody ModifyQuestionRequest request) {
    return questionService.modifyQuestion(request);
  }
}

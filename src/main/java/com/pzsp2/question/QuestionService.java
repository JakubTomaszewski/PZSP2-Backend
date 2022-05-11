package com.pzsp2.question;

import com.pzsp2.answer.Answer;
import com.pzsp2.answer.AnswerRepository;
import com.pzsp2.course.Course;
import com.pzsp2.course.CourseRepository;
import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Question> getAllQuestionsByCourseCode(String courseCode) {
        return questionRepository.getQuestionsByCourseCourseCode(courseCode);
    }

    public List<Question> getAllQuestionsByCourseName(String courseName) {
        return questionRepository.getQuestionsByCourseName(courseName);
    }

    public List<Question> getAllQuestionsByTeacherLogin(String login) {
        return questionRepository.getQuestionsByTeachersLogin(login);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public List<Question> getAllClosed() {
        String type = "c";
        return questionRepository.getQuestionsByTypeEqualsIgnoreCase(type);
    }

    public Question addQuestion(QuestionRequest request) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Course course = courseRepository.findCourseByCourseCode(request.getCourseCode());
        Teacher teacher = teacherRepository.getTeacherByUserUserId(request.getTeacherId());
        List<Answer> answers = new ArrayList<>();
        Question question = new Question();
        question.setType(request.getType());
        question.setContent(request.getContent());
        question.setCourse(course);
        question.setTeachers(teacher);
        question.setDateAdded(sqlDate);
        Question saved = questionRepository.save(question);
        if (request.getType().toLowerCase(Locale.ROOT).equals("c")) {
            for(int i = 0; i < request.getAnswers().size(); i++) {
                answers.add(answerRepository.save(new Answer(request.getAnswers().get(i),
                        request.getAreCorrect().get(i), saved)));
                }
            if(saved != null) { saved.setAnswers(answers); }
        }
        return saved;
    }

    public Question deleteQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
            questionRepository.delete(question.get());
            return question.get();
        }
        else
            throw new ApiRequestException("Question with id: " + id + " doesn't exist");
    }

    public Question modifyQuestion(ModifyQuestionRequest request) {
        Optional<Question> question = questionRepository.findById(request.getQuestionId());
        if(question.isPresent()) {
            if(courseRepository.existsCourseByCourseCode(request.getCourseCode())) {
                question.get().setCourse(courseRepository.findCourseByCourseCode(request.getCourseCode()));
            } else
                throw new ApiRequestException("Course with code: " + request.getCourseCode() + " doesn't exist");
            question.get().setType(request.getType());
            question.get().setContent(request.getContent());
            if(teacherRepository.existsByUserUserId(request.getTeacherId())) {
                question.get().setTeachers(teacherRepository.getTeacherByUserUserId(request.getTeacherId()));
            } else
                throw new ApiRequestException("Teacher with id: " + request.getTeacherId() + " doesn't exist");
            ArrayList<Answer> answers = new ArrayList<>(question.get().getAnswers());
            if(question.get().getType().toLowerCase(Locale.ROOT).equals("c")) {
                for(int i = 0; i < request.getAnswers().size(); i++) {
                    if(i+1 > answers.size()) {

                        answers.add(answerRepository.save(new Answer(request.getAnswers().get(i),
                                request.getAreCorrect().get(i), question.get())));
                    }
                    else {
                        answers.get(i).setContent(request.getAnswers().get(i));
                        answers.get(i).setIsCorrect(request.getAreCorrect().get(i));
                    }
                }
                question.get().setAnswers(answers);
            }
            else {
                answerRepository.deleteAll(answers);
                answers.clear();
            }
            question.get().setAnswers(answers);
            return question.get();
        }
        else
            throw new ApiRequestException("Question with id: " + request.getQuestionId() + " doesn't exist");


    }
}

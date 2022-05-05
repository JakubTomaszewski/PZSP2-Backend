package com.pzsp2.question;

import com.pzsp2.answer.Answer;
import com.pzsp2.answer.AnswerRepository;
import com.pzsp2.course.Course;
import com.pzsp2.course.CourseRepository;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public List<Question> getAllClosed() {
        String type = "c";
        return questionRepository.getQuestionsByTypeEqualsIgnoreCase(type);
    }

    public Question addQuestion(AddQuestionRequest request) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Course course = courseRepository.findCourseByCourseCode(request.getCourseCode());
        Teacher teacher = teacherRepository.findTeacherByUserUserId(1L);
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(request.getType(), request.getContent(), course, teacher);
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
}

package com.pzsp2.question;

import com.pzsp2.answer.Answer;
import com.pzsp2.answer.AnswerRepository;
import com.pzsp2.course.Course;
import com.pzsp2.course.CourseRepository;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Question> getAllQuestionsByCourseCode(String courseCode) {
        return questionRepository.findQuestionsByCourseCourseCode(courseCode);
    }

    public List<Question> getAllQuestionsByCourseName(String courseName) {
        return questionRepository.findQuestionsByCourseName(courseName);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question addQuestion(AddQuestionRequest request) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Course course = courseRepository.findCourseByCourseCode(request.getCourseCode());
        //request.getTeacherId() should be added temporarly 1 as id added
        Teacher teacher = teacherRepository.findTeacherByUserUserId(1L);
        List<Answer> answers = new ArrayList<>();
        Question question = new Question(request.getType(), request.getContent(), course, teacher);
        question.setDateAdded(sqlDate);
        if (question == null) {
            throw new ResourceNotFoundException();
        } else {
            Question question2 =  questionRepository.save(question);
            if (question2.getType().toLowerCase(Locale.ROOT).equals("c")) {
                for(int i = 0; i < request.getAnswers().size(); i++) {
                    answerRepository.save(
                            new Answer(request.getAnswers().get(i), request.getAreCorrect().get(i), question));
                }
            }
            return  questionRepository.getById(question2.getQuestionId());
        }

    }
}

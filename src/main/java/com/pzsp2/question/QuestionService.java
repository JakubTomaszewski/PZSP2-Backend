package com.pzsp2.question;

import com.pzsp2.answer.Answer;
import com.pzsp2.answer.AnswerRepository;
import com.pzsp2.course.Course;
import com.pzsp2.course.CourseRepository;
import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.user.teacher.Teacher;
import com.pzsp2.user.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    public List<Question> getAllCloseQuestionsByIds(List<Long> ids) {
        return questionRepository.getQuestionsByQuestionIdIsInAndType(ids, Question.CLOSED_QUESTION);
    }

    public List<Question> getAllOpenQuestionsByIds(List<Long> ids) {
        return questionRepository.getQuestionsByQuestionIdIsInAndType(ids, Question.OPEN_QUESTION);
    }

    /**
     * Create question based on request parameter and save it to the database. If its closed type of
     * question, then create answers based on request and save them to the database. Returned saved
     * question.
     *
     * @param request which provides information about question and answers to that question
     * @return saved question
     */
    public Question addQuestion(QuestionRequest request) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        //validate course
        Optional<Course> course = courseRepository.findById(request.getCourseCode());
        if (course.isEmpty()) {
            throw new ApiRequestException("Course with that code doesn't exist");
        }
        // TODO: validate teacher
        Teacher teacher = teacherRepository.getTeacherByUserUserId(request.getTeacherId());
        List<Answer> answers = new ArrayList<>();
        Question question = new Question();
        question.setType(request.getType());
        question.setContent(request.getContent());
        question.setCourse(course.get());
        question.setTeachers(teacher);
        question.setDateAdded(sqlDate);
        Question saved = questionRepository.save(question);
        if (request.getType().toLowerCase(Locale.ROOT).equals("c")) {
            for (int i = 0; i < request.getAnswers().size(); i++) {
                answers.add(
                        answerRepository.save(
                                new Answer(request.getAnswers().get(i), request.getAreCorrect().get(i), saved)));
            }
            saved.setAnswers(answers);
        }
        return saved;
    }

    /**
     * Delete question from database based on provided id. Throws User defined ApiRequestException if
     * question with provided id doesn't exist.
     *
     * @param id id of the question
     * @return deleted question
     */
    public Question deleteQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            questionRepository.delete(question.get());
            return question.get();
        } else throw new ApiRequestException("Question with id: " + id + " doesn't exist");
    }

    /**
     * Modify question based on request parameter. Throws User defined ApiRequestException if question
     * with id provided via request doesn't exist. If we change question type from closed to open one
     * then we delete all question from database which point to that question
     *
     * @param request which provides information about question's fields we want to change
     * @return modified question
     */
    public Question modifyQuestion(ModifyQuestionRequest request) {
        Optional<Question> question = questionRepository.findById(request.getQuestionId());
        if (question.isPresent()) {
            if (courseRepository.existsCourseByCourseCode(request.getCourseCode())) {
                question.get().setCourse(courseRepository.findCourseByCourseCode(request.getCourseCode()));
            } else
                throw new ApiRequestException(
                        "Course with code: " + request.getCourseCode() + " doesn't exist");
            question.get().setType(request.getType());
            question.get().setContent(request.getContent());
            if (teacherRepository.existsByUserUserId(request.getTeacherId())) {
                question
                        .get()
                        .setTeachers(teacherRepository.getTeacherByUserUserId(request.getTeacherId()));
            } else
                throw new ApiRequestException(
                        "Teacher with id: " + request.getTeacherId() + " doesn't exist");
            ArrayList<Answer> answers = new ArrayList<>(question.get().getAnswers());
            if (question.get().getType().toLowerCase(Locale.ROOT).equals("c")) {
                for (int i = 0; i < request.getAnswers().size(); i++) {
                    if (i + 1 > answers.size()) {

                        answers.add(
                                answerRepository.save(
                                        new Answer(
                                                request.getAnswers().get(i),
                                                request.getAreCorrect().get(i),
                                                question.get())));
                    } else {
                        answers.get(i).setContent(request.getAnswers().get(i));
                        answers.get(i).setIsCorrect(request.getAreCorrect().get(i));
                    }
                }
                question.get().setAnswers(answers);
            } else {
                answerRepository.deleteAll(answers);
                answers.clear();
            }
            question.get().setAnswers(answers);
            return question.get();
        } else
            throw new ApiRequestException(
                    "Question with id: " + request.getQuestionId() + " doesn't exist");
    }
}

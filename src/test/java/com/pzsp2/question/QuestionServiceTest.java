package com.pzsp2.question;

import com.pzsp2.answer.AnswerRepository;
import com.pzsp2.course.CourseRepository;
import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private QuestionService underTest;

    @BeforeEach
    void setUp() {
        underTest = new QuestionService(questionRepository, answerRepository, courseRepository, teacherRepository);
    }

    @Test
    void canGetAllQuestionsByTeachersLogin() {
        //given
        String login = "testLogin";
        //when
        underTest.getAllQuestionsByTeacherLogin(login);
        //then
        verify(questionRepository).getQuestionsByTeachersLogin(login);
    }

    @Test
    void canGetAllQuestionsByCourseCode() {
        //given
        String courseCode = "A01";
        //when
        underTest.getAllQuestionsByCourseCode(courseCode);
        //then
        verify(questionRepository).getQuestionsByCourseCourseCode(courseCode);
    }

    @Test
    void canGetAllQuestionsByCourseName() {
        //given
        String courseName = "Name";
        //when
        underTest.getAllQuestionsByCourseName(courseName);
        //then
        verify(questionRepository).getQuestionsByCourseName(courseName);
    }

    @Test
    void canGetAll() {
        //when
        underTest.getAll();
        //then
        verify(questionRepository).findAll();
    }

    @Test
    void canGetAllClosed() {
        //when
        underTest.getAllClosed();
        //then
        verify(questionRepository).getQuestionsByTypeEqualsIgnoreCase("c");
    }

    @Test
    void canAddQuestion() {
        //given
        String courseCode = "A04";
        String type = "c";
        String content = "testContent";
        Long id = 2L;
        List<String> answers = Arrays.asList("1", "2", "3", "4");
        List<Boolean> areCorrect = Arrays.asList(true, false, false, false);
        AddQuestionRequest request = new AddQuestionRequest(courseCode, type, content, id, answers, areCorrect);
        //when
        underTest.addQuestion(request);
        //then
        ArgumentCaptor<Question> questionArgumentCaptor = ArgumentCaptor.forClass(Question.class);
        verify(questionRepository).save(questionArgumentCaptor.capture());
        Question capturedQuestion = questionArgumentCaptor.getValue();
        assertThat(capturedQuestion.getContent()).isEqualTo(request.getContent());
        assertThat(capturedQuestion.getDateAdded()).isNotNull();
    }

    @Test
    void canDeleteQuestionWhenItExists() {
        //given
        Long testId = 1415L;
        Question question = new Question();
        question.setQuestionId(testId);
        given(questionRepository.findById(testId))
                .willReturn(Optional.of(question));
        //when
        underTest.deleteQuestionById(testId);
        //then
        verify(questionRepository).delete(question);
    }

    @Test
    void shouldThrowBadRequestExceptionWhenQuestionDoesNotExist() {
        //given
        Long testId = 1415L;
        given(questionRepository.findById(testId))
                .willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.deleteQuestionById(testId))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Question with id: " + testId + " doesn't exist");

    }

}
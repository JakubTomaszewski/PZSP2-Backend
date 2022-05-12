package com.pzsp2.question;

import com.pzsp2.answer.Answer;
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
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        QuestionRequest request = new QuestionRequest(courseCode, type, content, id, answers, areCorrect);
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
    void deleteShouldThrowBadRequestExceptionWhenQuestionDoesNotExist() {
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

    @Test
    void canModifyQuestionToClosedOne() {
        //given
        ModifyQuestionRequest request = new ModifyQuestionRequest();
        request.setQuestionId(12L);
        request.setCourseCode("A04");
        request.setType("c");
        request.setContent("testContent");
        request.setTeacherId(2L);
        request.setAnswers(Arrays.asList("1", "2", "3", "4"));
        request.setAreCorrect(Arrays.asList(true, false, false, false));
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        answer1.setContent("answer 1");
        answer2.setContent("answer 2");
        Collection<Answer> answers = new ArrayList<>(Arrays.asList(answer1, answer2));
        question.setAnswers(answers);
        given(questionRepository.findById(request.getQuestionId()))
                .willReturn(Optional.of(question));
        given(courseRepository.existsCourseByCourseCode(request.getCourseCode()))
                .willReturn(true);
        given(teacherRepository.existsByUserUserId(request.getTeacherId()))
                .willReturn(true);
        given(answerRepository.save(Mockito.any(Answer.class)))
                .willAnswer(i -> i.getArguments()[0]);
        //when
        underTest.modifyQuestion(request);
        //then
        verify(courseRepository).findCourseByCourseCode(request.getCourseCode());
        verify(teacherRepository).getTeacherByUserUserId(request.getTeacherId());
        assertThat(question.getContent()).isEqualTo(request.getContent());
        assertThat(question.getType()).isEqualTo(request.getType());
        ArrayList<Answer> questionAnswers = new ArrayList<>(question.getAnswers());
        if(question.getType().toLowerCase(Locale.ROOT).equals("c")) {
            for(int i = 0; i < question.getAnswers().size(); i++) {
                assertThat(questionAnswers.get(i).getContent()).isEqualTo(request.getAnswers().get(i));
                assertThat(questionAnswers.get(i).getIsCorrect()).isEqualTo(request.getAreCorrect().get(i));
            }
        }

    }

    @Test
    void canModifyQuestionToOpenedOne() {
        //given
        ModifyQuestionRequest request = new ModifyQuestionRequest();
        request.setQuestionId(12L);
        request.setCourseCode("A04");
        request.setType("o");
        request.setContent("testContent");
        request.setTeacherId(2L);
        Question question = new Question();
        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        answer1.setContent("answer 1");
        answer2.setContent("answer 2");
        Collection<Answer> answers = new ArrayList<>(Arrays.asList(answer1, answer2));
        question.setAnswers(answers);
        given(questionRepository.findById(request.getQuestionId()))
                .willReturn(Optional.of(question));
        given(courseRepository.existsCourseByCourseCode(request.getCourseCode()))
                .willReturn(true);
        given(teacherRepository.existsByUserUserId(request.getTeacherId()))
                .willReturn(true);
        //when
        underTest.modifyQuestion(request);
        //then
        verify(courseRepository).findCourseByCourseCode(request.getCourseCode());
        verify(teacherRepository).getTeacherByUserUserId(request.getTeacherId());
        assertThat(question.getContent()).isEqualTo(request.getContent());
        assertThat(question.getType()).isEqualTo(request.getType());
        assertThat(question.getAnswers().size()).isEqualTo(0);
    }

    @Test
    void modifyShouldThrowBadRequestExceptionWhenQuestionDoesNotExist() {
        //given
        ModifyQuestionRequest request = new ModifyQuestionRequest();
        request.setQuestionId(12515L);
        given(questionRepository.findById(request.getQuestionId()))
                .willReturn(Optional.empty());
        //when
        //then
        assertThatThrownBy(() -> underTest.modifyQuestion(request))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Question with id: " + request.getQuestionId() + " doesn't exist");
    }

    @Test
    void modifyShouldThrowBadRequestExceptionWhenTeacherDoesNotExist() {
        //given
        ModifyQuestionRequest request = new ModifyQuestionRequest();
        request.setQuestionId(12515L);
        request.setTeacherId(1534L);
        Question question = new Question();
        given(questionRepository.findById(request.getQuestionId()))
                .willReturn(Optional.of(question));
        given(courseRepository.existsCourseByCourseCode(request.getCourseCode()))
                .willReturn(true);
        given(teacherRepository.existsByUserUserId(request.getTeacherId()))
                .willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> underTest.modifyQuestion(request))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Teacher with id: " + request.getTeacherId() + " doesn't exist");
    }

    @Test
    void modifyShouldThrowBadRequestExceptionWhenCourseDoesNotExist() {
        //given
        ModifyQuestionRequest request = new ModifyQuestionRequest();
        request.setQuestionId(12515L);
        request.setCourseCode("TestCode");
        Question question = new Question();
        given(questionRepository.findById(request.getQuestionId()))
                .willReturn(Optional.of(question));
        given(courseRepository.existsCourseByCourseCode(request.getCourseCode()))
                .willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> underTest.modifyQuestion(request))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Course with code: " + request.getCourseCode() + " doesn't exist");
    }
}
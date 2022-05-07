package com.pzsp2.test;

import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.question.QuestionRepository;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.teacher.TeacherRepository;
import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.testquestion.TestQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestServiceTest {

    @Mock
    private TestRepository testRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private TestQuestionRepository testQuestionRepository;
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private TestService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TestService(testRepository, questionRepository, testQuestionRepository, teacherRepository);
    }


    @Test
    void canGetAllTests() {
        //given
        //when
        underTest.getAllTests();
        //then
        verify(testRepository).findAll();
    }

    @Test
    void canAddTest() {
        //given
        List<Long> ids = new ArrayList<>();
        ids.add(337283L);
        Long teacherId = 1L;
        java.sql.Date date = new Date(1234);
        AddTestRequest request = new AddTestRequest(ids, teacherId, date, null);
        Teacher teacher = new Teacher();
        given(questionRepository.countQuestionsByQuestionIdIn(request.getQuestionsId()))
                .willReturn(request.getQuestionsId().size());
        //when
        when(teacherRepository.getById(request.getTeacherId())).thenReturn(teacher);
        when(testRepository.save(Mockito.any(com.pzsp2.test.Test.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        //then

        underTest.addTest(request);
        ArgumentCaptor<com.pzsp2.test.Test> testArgumentCaptor = ArgumentCaptor.forClass(com.pzsp2.test.Test.class);
        verify(testRepository).save(testArgumentCaptor.capture());
        com.pzsp2.test.Test captured = testArgumentCaptor.getValue();
        assertThat(captured.getStartDate()).isNotNull();
    }

    @Test
    void shouldThrowBadRequestExceptionWhenDuplicatedOrInvalidQuestionIds() {
        //given
        List<Long> ids = new ArrayList<>();
        ids.add(337283L);
        Long teacherId = 1L;
        java.sql.Date date = new Date(1234);
        AddTestRequest request = new AddTestRequest(ids, teacherId, date, null);
        given(questionRepository.countQuestionsByQuestionIdIn(request.getQuestionsId()))
                .willReturn(0);
        //when
        //then
        assertThatThrownBy(() -> underTest.addTest(request))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Not valid questions' ids");

    }
}
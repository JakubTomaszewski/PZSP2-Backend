package com.pzsp2.test;

import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.question.QuestionRepository;
import com.pzsp2.testquestion.TestQuestionRepository;
import com.pzsp2.user.teacher.Teacher;
import com.pzsp2.user.teacher.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        underTest =
                new TestService(
                        testRepository, questionRepository, testQuestionRepository, teacherRepository);
    }

    @Test
    void canGetAllTests() {
        // given
        // when
        underTest.getAllTests();
        // then
        verify(testRepository).findAll();
    }

    @Test
    void canGetTestByIdIfTestExists() {
        // given
        Long testId = 12425L;
        com.pzsp2.test.Test test = new com.pzsp2.test.Test();
        given(testRepository.findById(testId)).willReturn(Optional.of(test));
        // when
        underTest.getTestById(testId);
        // then
        verify(testRepository).findById(testId);
    }

    void shouldThrowBadRequestExceptionWhenTestDoesntExist() {
        Long testId = 12425L;
        given(testRepository.findById(testId)).willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> underTest.getTestById(testId))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Test doesn't exist");
    }

    @Test
    void canAddTest() {
        // given
        List<Long> ids = new ArrayList<>();
        ids.add(337283L);
        String name = "testName";
        Long teacherId = 1L;
        java.sql.Timestamp date = new Timestamp(1234);
        AddTestRequest request = new AddTestRequest(name, ids, teacherId, date, null);
        Teacher teacher = new Teacher();
        given(questionRepository.countQuestionsByQuestionIdIn(request.getQuestionsId()))
                .willReturn(request.getQuestionsId().size());
        // when
        when(teacherRepository.getTeacherByUserUserId(request.getTeacherId())).thenReturn(teacher);
        when(testRepository.save(Mockito.any(com.pzsp2.test.Test.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        // then

        underTest.addTest(request);
        ArgumentCaptor<com.pzsp2.test.Test> testArgumentCaptor =
                ArgumentCaptor.forClass(com.pzsp2.test.Test.class);
        verify(testRepository).save(testArgumentCaptor.capture());
        com.pzsp2.test.Test captured = testArgumentCaptor.getValue();
        assertThat(captured.getStartDate()).isNotNull();
    }

    @Test
    void shouldThrowBadRequestExceptionWhenDuplicatedOrInvalidQuestionIds() {
        // given
        List<Long> ids = new ArrayList<>();
        String name = "testName";
        ids.add(337283L);
        Long teacherId = 1L;
        java.sql.Timestamp date = new Timestamp(1234);
        AddTestRequest request = new AddTestRequest(name, ids, teacherId, date, null);
        given(questionRepository.countQuestionsByQuestionIdIn(request.getQuestionsId())).willReturn(0);
        // when
        // then
        assertThatThrownBy(() -> underTest.addTest(request))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Not valid questions' ids");
    }
}

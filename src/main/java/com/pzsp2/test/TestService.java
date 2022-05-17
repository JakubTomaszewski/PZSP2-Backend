package com.pzsp2.test;

import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.question.Question;
import com.pzsp2.question.QuestionRepository;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.teacher.TeacherRepository;
import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.testquestion.TestQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class TestService {
  private final TestRepository testRepository;
  private final QuestionRepository questionRepository;
  private final TestQuestionRepository testQuestionRepository;
  private final TeacherRepository teacherRepository;

  public List<Test> getAllTests() {
    return testRepository.findAll();
  }

  public Test getTestById(Long id) {
    return testRepository.getTestByTestId(id);
  }

  /**
   * Create test based on request parameter, then create test questions based on created test and
   * provided questions via request and save them and the test to the database.
   * @param request
   * @return saved test
   */
  public Test addTest(AddTestRequest request) {
    // validate questions!
    int numberOfIds = questionRepository.countQuestionsByQuestionIdIn(request.getQuestionsId());
    if (numberOfIds != request.getQuestionsId().size()) {
      throw new ApiRequestException("Not valid questions' ids");
    }

    // create date
    java.sql.Date startDate;
    if (request.getStartDate() == null) {
      java.util.Date tmp = new java.util.Date();
      startDate = new java.sql.Date(tmp.getTime());
    } else {
      startDate = request.getStartDate();
    }

    Teacher teacher = teacherRepository.getTeacherByUserUserId(request.getTeacherId());

    // create test
    Test test = new Test(startDate, request.getEndDate(), teacher);

    // save test
    test = testRepository.save(test);
    // make link
    String link = Test.solveLink + test.getTestId();
    test.setLink(link);
    // create test questions and add them to test
    List<Question> questions = questionRepository.findByQuestionIdIn(request.getQuestionsId());
    List<TestQuestion> testQuestions = new ArrayList<>();
    for (int i = 0; i < questions.size(); i++) {
      TestQuestion testQuestion =
          new TestQuestion(
              questions.get(i).getQuestionId(), i + 1, test.getTestId(), questions.get(i), test);
      testQuestionRepository.save(testQuestion);
      testQuestions.add(testQuestion);
    }
    if (test != null) {
      test.setTestQuestions(testQuestions);
    }
    return test;
  }
}

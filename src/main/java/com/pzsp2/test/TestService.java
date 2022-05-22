package com.pzsp2.test;

import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.question.Question;
import com.pzsp2.question.QuestionRepository;
import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.testquestion.TestQuestionRepository;
import com.pzsp2.user.teacher.Teacher;
import com.pzsp2.user.teacher.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class TestService {
    private static final Integer PASSWORD_LENGTH = 8;
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final TeacherRepository teacherRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {

        Optional<Test> testOptional = testRepository.findById(id);
        if (testOptional.isPresent())
            return testOptional.get();
        else
            throw new ApiRequestException("Test doesn't exist");
    }

    public Test getTestByPassword(String password) {
        Optional<Test> testOptional = testRepository.findByPassword(password);
        if (testOptional.isPresent())
            return testOptional.get();
        else
            throw new ApiRequestException("Invalid password");
    }

    /**
     * Create test based on request parameter, then create test questions based on created test and
     * provided questions via request and save them and the test to the database.
     *
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
        java.sql.Timestamp startDate;
        if (request.getStartDate() == null) {
            java.util.Date tmp = new java.util.Date();
            startDate = new java.sql.Timestamp(tmp.getTime());
        } else {
            startDate = request.getStartDate();
        }

        Teacher teacher = teacherRepository.getTeacherByUserUserId(request.getTeacherId());

        // create test
        Test test = new Test(startDate, request.getEndDate(), teacher);

        // save test
        test = testRepository.save(test);
        // make link
        List<Test> tests = testRepository.findAll();
        List<String> passwords = new ArrayList<>();
        for (Test temp : tests) {
            passwords.add(temp.getPassword());
        }
        boolean doesNewPasswordAlreadyExists = true;
        while (doesNewPasswordAlreadyExists) {
            String password = TestPasswordGenerator.generateRandomPassword(PASSWORD_LENGTH);
            if (!passwords.contains(password)) {
                doesNewPasswordAlreadyExists = false;
                test.setPassword(password);
            }
        }
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

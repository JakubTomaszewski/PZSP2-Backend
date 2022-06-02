package com.pzsp2.solution;

import com.pzsp2.answer.Answer;
import com.pzsp2.answer.AnswerService;
import com.pzsp2.exception.ApiRequestException;
import com.pzsp2.question.Question;
import com.pzsp2.question.QuestionService;
import com.pzsp2.test.Test;
import com.pzsp2.test.TestService;
import com.pzsp2.user.student.Student;
import com.pzsp2.user.student.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@Service
@Transactional
public class SolutionService {
    private static final long ACCEPTABLE_DELAY = 10;
    private final SolutionRepository solutionRepository;
    private final StudentService studentService;
    private final TestService testService;
    private final QuestionService questionService;
    private final AnswerService answerService;


    public List<Solution> checkClosedQuestions(Long testId) {
        List<Solution> solutions = new ArrayList<>(testService.getTestById(testId).getSolutions());
        for (Solution solution : solutions) {
            if (solution.getQuestion().getType().equals(Question.CLOSED_QUESTION)) {
                if (solution.getAnswer().getIsCorrect()) solution.setPoints(1);
            }
        }
        return solutions;
    }

    public List<Solution> checkOpenQuestions(manualCheckRequest request) {
        List<Solution> solutions = solutionRepository.getAllByIdTestId(request.getTestId());
        Set<SolutionPK> keys = request.getGrades().keySet();
        for (Solution solution : solutions) {
            if (keys.contains(solution.getId())) {
                solution.setPoints(request.getGrades().get(solution.getId()));
            }
        }
        return solutions;
    }

    private StudentTestSolutionPOJO createPOJOFromSolutions(Student student, List<Solution> solutions) {
        Integer pointsFromClosedQuestions = 0;
        Integer allPointsFromClosedQuestions = 0;
        Integer pointsFromOpenQuestions = 0;
        List<SolutionPOJO> solutionPOJOS = new ArrayList<>();
        for (Solution solution : solutions) {
            if (solution.getQuestion().getType().equals(Question.CLOSED_QUESTION)) {
                allPointsFromClosedQuestions++;
                if (solution.getAnswer().getIsCorrect()) pointsFromClosedQuestions++;
            } else pointsFromOpenQuestions += solution.getPoints();
            solutionPOJOS.add(
                    new SolutionPOJO(
                            solution.getId(),
                            solution.getQuestion().getContent(),
                            solution.getQuestion().getAnswers(),
                            solution.getContent(),
                            solution.getAnswer()));
        }
        return new StudentTestSolutionPOJO(
                student.getUserFirstName(),
                student.getUserLastName(),
                student.getIdNumber(),
                pointsFromClosedQuestions,
                pointsFromOpenQuestions,
                allPointsFromClosedQuestions,
                solutionPOJOS);
    }

    public ResponseEntity<StudentTestSolutionPOJO> getAllTestSolutionsMadeByStudent(GetStudentTestSolutionRequest request) {
        Optional<Student> studentOptional = studentService.getStudentOptional(
                request.getName(),
                request.getSurname(),
                request.getStudentIdNum());
        if (studentOptional.isEmpty()) {
            throw new ApiRequestException(
                    "Student: " + request.getName() + " " + request.getSurname() + " doesn't exist");
        } else {
            Student student = studentOptional.get();
            Long studentId = student.getUserUserId();
            List<Solution> solutions = solutionRepository.getAllByIdTestIdAndIdUserId(request.getTestId(), studentId);
            return new ResponseEntity<>(
                    createPOJOFromSolutions(student, solutions),
                    HttpStatus.OK);
        }
    }

    public ResponseEntity<StudentTestSolutionWithTestNamePOJO> getAllSolutionsByTestId(Long id) {
        String testName = testService.getTestById(id).getName();
        List<Solution> testSolutions = solutionRepository.getAllByIdTestId(id);
        Map<Student, List<Solution>> studentsTestSolutions = new HashMap<>();
        for (Solution solution : testSolutions) {
            Student student = solution.getStudent();
            List<Solution> temp;
            if (studentsTestSolutions.containsKey(student)) {
                temp = studentsTestSolutions.get(student);
            } else {
                temp = new ArrayList<>();
            }
            temp.add(solution);
            studentsTestSolutions.put(student, temp);
        }
        List<StudentTestSolutionPOJO> response = new ArrayList<>();
        for (Student student : studentsTestSolutions.keySet()) {
            response.add(createPOJOFromSolutions(student, studentsTestSolutions.get(student)));
        }
        StudentTestSolutionWithTestNamePOJO finalResponse = new StudentTestSolutionWithTestNamePOJO(testName, response);
        return new ResponseEntity<>(finalResponse, HttpStatus.OK);

    }


    public List<Solution> addSolutions(SaveSolutionRequest request) {
        Test test = testService.getTestById(request.getTestId());
        LocalDateTime currentTime = LocalDateTime.now();
        Timestamp sqlEndDate = test.getEndDate();
        LocalDateTime endTime = sqlEndDate.toLocalDateTime();
        if (currentTime.isAfter(endTime.plusSeconds(ACCEPTABLE_DELAY))) {
            throw new ApiRequestException("Its after tests deadline!");
        } else {
            Student student = studentService.getOrCreateStudent(
                    request.getName(),
                    request.getSurname(),
                    request.getStudentIdNum());
            List<Solution> solutions = new ArrayList<>();
            List<Question> openQuestions = questionService.getAllOpenQuestionsByIds(
                    List.copyOf(request.getOpenQuestionAnswers().keySet()));
            List<Question> closeQuestions = questionService.getAllCloseQuestionsByIds(
                    List.copyOf(request.getCloseQuestionAnswers().keySet()));
            for (Question question : openQuestions) {
                String content = request.getOpenQuestionAnswers().get(question.getQuestionId());
                Solution solution = new Solution();
                solution.setId(new SolutionPK(question.getQuestionId(), test.getTestId(), student.getUserUserId()));
                solution.setContent(content);
                solution.setQuestion(question);
                solution.setStudent(student);
                solution.setTest(test);
                solutionRepository.save(solution);
                solutions.add(solution);
            }
            for (Question question : closeQuestions) {
                Long answerId = request.getCloseQuestionAnswers().get(question.getQuestionId());
                Answer answer = answerService.getAnswerById(answerId);
                Solution solution = new Solution();
                solution.setId(new SolutionPK(question.getQuestionId(), test.getTestId(), student.getUserUserId()));
                solution.setAnswer(answer);
                solution.setQuestion(question);
                solution.setStudent(student);
                solution.setTest(test);
                solutionRepository.save(solution);
                solutions.add(solution);
            }
            return solutions;
        }
    }

}

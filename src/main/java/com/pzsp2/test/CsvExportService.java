package com.pzsp2.test;

import com.pzsp2.solution.Solution;
import com.pzsp2.solution.SolutionService;
import com.pzsp2.solution.StudentTestSolutionPOJO;
import com.pzsp2.user.student.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CsvExportService {
//    private static final Logger log = new Logger;

    private final TestService testService;
    private final SolutionService solutionService;

    public CsvExportService(TestService testService, SolutionService solutionService) {
        this.testService = testService;
        this.solutionService = solutionService;
    }

    public void writeSolutionsToCSV (Writer writer, Long testId) {
        Test test = testService.getTestById(testId);
        List<Solution> solutions = new ArrayList<>(test.getSolutions());
        Map<Student, List<Solution>> studentsTestSolutions = new HashMap<>();
        for (Solution solution : solutions) {
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
        List<StudentTestSolutionPOJO> solutionsList = new ArrayList<>();
        for (Student student : studentsTestSolutions.keySet()) {
            solutionsList.add(solutionService.createPOJOFromSolutions(student, studentsTestSolutions.get(student)));
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(
                    "Surname", "Name", "Student ID", "Points"
            );
            for (StudentTestSolutionPOJO solution : solutionsList) {
                Integer pointsSum = solution.getClosedQuestionsPoints() + solution.getOpenQuestionsPoints();
                csvPrinter.printRecord(
                        solution.getStudentSurname(),
                        solution.getStudentName(),
                        solution.getStudentId(),
                        pointsSum);
            }
        } catch (IOException e) {
//            log.log("Error While writing SCV");
        }
    }
}

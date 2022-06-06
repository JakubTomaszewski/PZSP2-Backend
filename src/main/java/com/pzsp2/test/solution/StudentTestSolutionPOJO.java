package com.pzsp2.test.solution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentTestSolutionPOJO {
    private String studentName;
    private String studentSurname;
    private String studentId;
    private Integer closedQuestionsPoints;
    private Integer openQuestionsPoints;
    private Integer maxClosedQuestionsPoints;
    private List<SolutionPOJO> studentSolutions;
}

package com.pzsp2.solution;

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
    private List<SolutionPOJO> studentSolutions;
    private Integer studentPoints;
    private Integer maxPoints;
}

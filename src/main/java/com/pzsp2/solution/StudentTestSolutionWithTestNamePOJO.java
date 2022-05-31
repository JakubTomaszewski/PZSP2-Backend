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
public class StudentTestSolutionWithTestNamePOJO {
    private String testName;
    private List<StudentTestSolutionPOJO> solutions;
}

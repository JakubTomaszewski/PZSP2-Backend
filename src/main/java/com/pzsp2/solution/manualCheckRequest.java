package com.pzsp2.solution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class manualCheckRequest {
    private Long testId;
    private List<SolutionPK> solutionIds;
    private List<Integer> grades;
}

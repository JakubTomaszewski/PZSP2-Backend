package com.pzsp2.solution;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
public class manualCheckRequest {
    private Long testId;
    private HashMap<SolutionPK, Integer> grades;
}

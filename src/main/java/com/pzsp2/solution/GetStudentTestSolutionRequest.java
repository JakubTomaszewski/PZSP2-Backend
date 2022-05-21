package com.pzsp2.solution;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetStudentTestSolutionRequest {
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String studentIdNum;

    @NotNull
    private Long testId;
}

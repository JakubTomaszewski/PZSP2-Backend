package com.pzsp2.solution;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class SaveSolutionRequest extends GetStudentTestSolutionRequest {
    private HashMap<Long, String> openQuestionAnswers;
    private HashMap<Long, Long> closeQuestionAnswers;

    public SaveSolutionRequest(
            String name,
            String surname,
            String studentIdNum,
            Long testId,
            HashMap<Long, String> openQuestionAnswers,
            HashMap<Long, Long> closeQuestionAnswers) {
        super(name, surname, studentIdNum, testId);
        this.openQuestionAnswers = openQuestionAnswers;
        this.closeQuestionAnswers = closeQuestionAnswers;
    }
}

package com.pzsp2.solution;

import com.pzsp2.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class SolutionPOJO {
    private SolutionPK solutionId;
    private String questionContent;
    private Collection<Answer> questionAnswers;
    private String studentAnswerContent;
    private Answer studentAnswer;
}

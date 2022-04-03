package com.pzsp2.solution;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class SolutionPK implements Serializable {
    private Long questionId;
    private Long testId;
    private Long userId;

    @Column(name = "QUESTION_ID")
    @Id
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Column(name = "TEST_ID")
    @Id
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Column(name = "USER_ID")
    @Id
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionPK that = (SolutionPK) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(testId, that.testId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId, userId);
    }
}

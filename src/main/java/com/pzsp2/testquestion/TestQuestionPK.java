package com.pzsp2.testquestion;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TestQuestionPK implements Serializable {
    private Long questionId;
    private Long testId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestionPK that = (TestQuestionPK) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId);
    }
}

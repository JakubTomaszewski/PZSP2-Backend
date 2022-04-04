package com.pzsp2.testquestion;

import com.pzsp2.question.Question;
import com.pzsp2.test.Test;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TEST_QUESTIONS", schema = "PZSP04")
@IdClass(TestQuestionPK.class)
public class TestQuestion {
    private Long questionId;
    private Integer questionNo;
    private Long testId;
    private Question question;
    private Test test;

    @Id
    @Column(name = "QUESTION_ID")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "QUESTION_NO")
    public Integer getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Integer questionNo) {
        this.questionNo = questionNo;
    }

    @Id
    @Column(name = "TEST_ID")
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
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(questionId, that.questionId) && Objects.equals(questionNo, that.questionNo) && Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionNo, testId);
    }

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID",
            nullable = false, insertable = false, updatable = false)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    @JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID",
            nullable = false, insertable = false, updatable = false)
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}

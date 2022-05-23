package com.pzsp2.testquestion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.question.Question;
import com.pzsp2.test.Test;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
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

    @Basic
    @Column(name = "QUESTION_NO")
    public Integer getQuestionNo() {
        return questionNo;
    }

    @Id
    @Column(name = "TEST_ID")
    public Long getTestId() {
        return testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(questionId, that.questionId)
                && Objects.equals(questionNo, that.questionNo)
                && Objects.equals(testId, that.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, questionNo, testId);
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(
            name = "QUESTION_ID",
            referencedColumnName = "QUESTION_ID",
            nullable = false,
            insertable = false,
            updatable = false)
    public Question getQuestion() {
        return question;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            name = "TEST_ID",
            referencedColumnName = "TEST_ID",
            nullable = false,
            insertable = false,
            updatable = false)
    public Test getTest() {
        return test;
    }
}

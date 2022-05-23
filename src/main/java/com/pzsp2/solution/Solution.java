package com.pzsp2.solution;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.answer.Answer;
import com.pzsp2.question.Question;
import com.pzsp2.test.Test;
import com.pzsp2.user.student.Student;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SOLUTIONS", schema = "PZSP04")
@IdClass(SolutionPK.class)
public class Solution {
    private Long questionId;
    private Long testId;
    private String content;
    private Long userId;
    // REPAIR questions to question !!
    private Question question;
    private Test test;
    private Answer answer;
    private Student student;

    @Id
    @Column(name = "QUESTION_ID")
    public Long getQuestionId() {
        return questionId;
    }

    @Id
    @Column(name = "TEST_ID")
    public Long getTestId() {
        return testId;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    @Id
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(questionId, solution.questionId)
                && Objects.equals(testId, solution.testId)
                && Objects.equals(content, solution.content)
                && Objects.equals(userId, solution.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId, content, userId);
    }

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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ANSWER_ID")
    public Answer getAnswer() {
        return answer;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(
            name = "USER_ID",
            referencedColumnName = "USER_USER_ID",
            nullable = false,
            insertable = false,
            updatable = false)
    public Student getStudent() {
        return student;
    }
}

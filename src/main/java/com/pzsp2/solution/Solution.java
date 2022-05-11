package com.pzsp2.solution;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pzsp2.student.Student;
import com.pzsp2.test.Test;
import com.pzsp2.answer.Answer;
import com.pzsp2.question.Question;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SOLUTIONS", schema = "PZSP04")
@IdClass(SolutionPK.class)
public class Solution {
    private Long questionId;
    private Long testId;
    private String content;
    private Long userId;
    private Question questions;
    private Test test;
    private Answer answers;
    private Student student;

    @Id
    @Column(name = "QUESTION_ID")
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Id
    @Column(name = "TEST_ID")
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @Column(name = "USER_ID")
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
        Solution solution = (Solution) o;
        return Objects.equals(questionId, solution.questionId) && Objects.equals(testId, solution.testId) && Objects.equals(content, solution.content) && Objects.equals(userId, solution.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, testId, content, userId);
    }

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", nullable = false,
            insertable = false, updatable = false)
    public Question getQuestions() {
        return questions;
    }

    public void setQuestions(Question questions) {
        this.questions = questions;
    }

    @ManyToOne
    @JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID", nullable = false,
            insertable = false, updatable = false)
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ANSWER_ID")
    public Answer getAnswers() {
        return answers;
    }

    public void setAnswers(Answer answers) {
        this.answers = answers;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_USER_ID", nullable = false,
            insertable = false, updatable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

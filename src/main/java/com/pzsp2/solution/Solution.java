package com.pzsp2.solution;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.answer.Answer;
import com.pzsp2.question.Question;
import com.pzsp2.test.Test;
import com.pzsp2.user.student.Student;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@AllArgsConstructor
@Table(name = "SOLUTIONS", schema = "PZSP04")
public class Solution {
    private SolutionPK id;
    private String content;
    private Integer points;
    private Question question;
    private Test test;
    private Answer answer;
    private Student student;

    public Solution() {
        points = 0;
    }

    @EmbeddedId
    public SolutionPK getId() {
        return id;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    @Basic
    @Column(name = "POINTS")
    public Integer getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(id, solution.id)
                && Objects.equals(content, solution.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @MapsId("questionId")
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
    @MapsId("testId")
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
    @MapsId("userId")
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

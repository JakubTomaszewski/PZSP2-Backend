package com.pzsp2.answer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pzsp2.question.Question;
import com.pzsp2.solution.Solution;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ANSWERS", schema = "PZSP04")
public class Answer {
    private Long answerId;
    private String content;
    private Boolean isCorrect;
    private Question question;
    private Collection<Solution> solutions;

    public Answer(String content, Boolean isCorrect, Question question) {
        this.content = content;
        this.isCorrect = isCorrect;
        this.question = question;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWER_ID")
    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "IS_CORRECT")
    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(answerId, answer.answerId) && Objects.equals(content, answer.content) && Objects.equals(isCorrect, answer.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, content, isCorrect);
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", nullable = false)
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @OneToMany(mappedBy = "answers")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Collection<Solution> solutions) {
        this.solutions = solutions;
    }
}

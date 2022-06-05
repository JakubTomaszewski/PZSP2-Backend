package com.pzsp2.answer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pzsp2.question.Question;
import com.pzsp2.solution.Solution;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Table(name = "AVAILABLE_ANSWERS", schema = "PZSP04")
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

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    @Basic
    @Column(name = "IS_CORRECT", columnDefinition = "CHAR(1)")
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "QUESTION_ID", nullable = false)
    public Question getQuestion() {
        return question;
    }

    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy = "answer")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Answer answer = (Answer) o;
        return getAnswerId() != null && Objects.equals(getAnswerId(), answer.getAnswerId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

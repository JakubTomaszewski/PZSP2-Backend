package com.pzsp2.question;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.course.Course;
import com.pzsp2.question.answer.Answer;
import com.pzsp2.question.multimedia.Multimedia;
import com.pzsp2.test.solution.Solution;
import com.pzsp2.test.testquestion.TestQuestion;
import com.pzsp2.user.teacher.Teacher;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "QUESTIONS", schema = "PZSP04")
public class Question {
    public final static String OPEN_QUESTION = "o";
    public final static String CLOSED_QUESTION = "c";
    private Long questionId;
    private String type;
    private String content;
    private Date dateAdded;
    private Date dateLastUsed;
    private Collection<Answer> answers;
    private Collection<Multimedia> multimedia;
    private Course course;
    private Teacher teachers;
    private Collection<Solution> solutions;
    private Collection<TestQuestion> testQuestions;

    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getQuestionId() {
        return questionId;
    }

    @Basic
    @Column(name = "TYPE", length = 1, columnDefinition = "char")
    public String getType() {
        return type;
    }

    @Basic
    @Column(name = "CONTENT")
    public String getContent() {
        return content;
    }

    @Basic
    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return dateAdded;
    }

    @Basic
    @Column(name = "DATE_LAST_USED")
    public Date getDateLastUsed() {
        return dateLastUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId)
                && Objects.equals(type, question.type)
                && Objects.equals(content, question.content)
                && Objects.equals(dateAdded, question.dateAdded)
                && Objects.equals(dateLastUsed, question.dateLastUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, type, content, dateAdded, dateLastUsed);
    }

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "question")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    public Collection<Multimedia> getMultimedia() {
        return multimedia;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_CODE", referencedColumnName = "COURSE_CODE", nullable = false)
    public Course getCourse() {
        return course;
    }

    @ManyToOne()
    @JsonBackReference
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Teacher getTeachers() {
        return teachers;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "question")
    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }
}

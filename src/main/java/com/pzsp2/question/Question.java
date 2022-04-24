package com.pzsp2.question;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.solution.Solution;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.answer.Answer;
import com.pzsp2.course.Course;
import com.pzsp2.multimedia.Multimedia;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@SequenceGenerator(name="seq", initialValue=1)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUESTIONS", schema = "PZSP04")
public class Question {
    public static String openQuestion = "O";
    public static String closedQuestion = "C";
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

    public Question(String type, String content, Date dateAdded, Collection<Answer> answers,
                    Collection<Multimedia> multimedia, Course course, Teacher teacher) {
        this.type = type;
        this.content = content;
        this.dateAdded = dateAdded;
        this.answers = answers;
        this.multimedia = multimedia;
        this.course = course;
        this.teachers = teacher;
    }

    public Question(Long id, String type, String content, Course course, Teacher teacher) {
        this.questionId = id;
        this.type = type;
        this.content = content;
        this.course = course;
        this.teachers = teacher;
    }

    public Question(String type, String content, Course course, Teacher teacher) {
        this.type = type;
        this.content = content;
        this.course = course;
        this.teachers = teacher;
    }

    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "TYPE" )
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Basic
    @Column(name = "DATE_LAST_USED")
    public Date getDateLastUsed() {
        return dateLastUsed;
    }

    public void setDateLastUsed(Date dateLastUsed) {
        this.dateLastUsed = dateLastUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionId, question.questionId) && Objects.equals(type, question.type) && Objects.equals(content, question.content) && Objects.equals(dateAdded, question.dateAdded) && Objects.equals(dateLastUsed, question.dateLastUsed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, type, content, dateAdded, dateLastUsed);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "question")
    public Collection<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "question")
    public Collection<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Collection<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_CODE", referencedColumnName = "COURSE_CODE", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne()
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_USER_ID", nullable = false)
    public Teacher getTeachers() {
        return teachers;
    }

    public void setTeachers(Teacher teachers) {
        this.teachers = teachers;
    }

    @OneToMany(mappedBy = "questions")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Collection<Solution> solutions) {
        this.solutions = solutions;
    }

    @OneToMany(mappedBy = "question")
    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Collection<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }
}

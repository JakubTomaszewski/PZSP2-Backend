package com.pzsp2.test;

import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.solution.Solution;
import com.pzsp2.teacher.Teacher;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "TESTS", schema = "PZSP04")
public class Test {
    private Long testId;
    private Date startDate;
    private Date endDate;
    private String link;
    private Collection<Solution> solutions;
    private Teacher teacher;
    private Collection<TestQuestion> testQuestions;

    @Id
    @Column(name = "TEST_ID")
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return Objects.equals(testId, test.testId) && Objects.equals(startDate, test.startDate) && Objects.equals(endDate, test.endDate) && Objects.equals(link, test.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, startDate, endDate, link);
    }

    @OneToMany(mappedBy = "test")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Collection<Solution> solutions) {
        this.solutions = solutions;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_USER_ID", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @OneToMany(mappedBy = "test")
    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(Collection<TestQuestion> testQuestions) {
        this.testQuestions = testQuestions;
    }
}

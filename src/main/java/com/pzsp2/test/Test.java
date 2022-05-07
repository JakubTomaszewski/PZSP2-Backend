package com.pzsp2.test;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.testquestion.TestQuestion;
import com.pzsp2.solution.Solution;
import com.pzsp2.teacher.Teacher;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "TESTS", schema = "PZSP04")
public class Test {
    private Long testId;
    private Date startDate;
    private Date endDate;
    private String link;
    private Collection<Solution> solutions;
    private Teacher teacher;
    private Collection<TestQuestion> testQuestions;

    public Test(Date startDate, Date endDate, Teacher teacher) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }

    @Id
    @Column(name = "TEST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    public Long getTestId() {
        return testId;
    }

    @Basic
    @Column(name = "START_DATE")
    public Date getStartDate() {
        return startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Date getEndDate() {
        return endDate;
    }

    @Basic
    @Column(name = "LINK")
    public String getLink() {
        return link;
    }

    @OneToMany(mappedBy = "test")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_USER_ID", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "test")
    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

}

package com.pzsp2.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.test.solution.Solution;
import com.pzsp2.test.testquestion.TestQuestion;
import com.pzsp2.user.teacher.Teacher;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

interface Password {
    String getPassword();
}

@Entity
@Setter
@NoArgsConstructor
@Table(name = "TESTS", schema = "PZSP04")
public class Test {
    static final String solveLink = "/api/tests/solve/";
    private Long testId;
    private Timestamp startDate;
    private Timestamp endDate;
    private String password;
    private String name;
    private Collection<Solution> solutions;
    private Teacher teacher;
    private Collection<TestQuestion> testQuestions;

    public Test(Timestamp startDate, Timestamp endDate, Teacher teacher) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }

    @Id
    @Column(name = "TEST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getTestId() {
        return testId;
    }

    @Basic
    @Column(name = "START_DATE")
    public Timestamp getStartDate() {
        return startDate;
    }

    @Basic
    @Column(name = "END_DATE")
    public Timestamp getEndDate() {
        return endDate;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "LINK")
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "test")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "test")
    public Collection<TestQuestion> getTestQuestions() {
        return testQuestions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Test test = (Test) o;
        return getTestId() != null && Objects.equals(getTestId(), test.getTestId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.pzsp2.teacher;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.test.Test;
import com.pzsp2.coursesteacher.CoursesTeachers;
import com.pzsp2.question.Question;
import com.pzsp2.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEACHERS", schema = "PZSP04")
public class Teacher extends User{
    private String login;
    private String password;
    private Collection<CoursesTeachers> coursesTeachers;
    private Collection<Question> questions;
    private Collection<Test> tests;

    @Basic
    @Column(name = "TEACH_LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String teachLogin) {
        this.login = teachLogin;
    }

    @Basic
    @Column(name = "TEACH_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String teachPassword) {
        this.password = teachPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(login, teacher.login) && Objects.equals(password, teacher.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @OneToMany(mappedBy = "teacher")
    public Collection<CoursesTeachers> getCoursesTeachers() {
        return coursesTeachers;
    }

    public void setCoursesTeachers(Collection<CoursesTeachers> coursesTeachers) {
        this.coursesTeachers = coursesTeachers;
    }

    @OneToMany(mappedBy = "teachers")
    @JsonManagedReference
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @OneToMany(mappedBy = "teacher")
    public Collection<Test> getTests() {
        return tests;
    }

    public void setTests(Collection<Test> tests) {
        this.tests = tests;
    }
}

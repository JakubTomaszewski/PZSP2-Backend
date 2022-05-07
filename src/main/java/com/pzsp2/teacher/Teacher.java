package com.pzsp2.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.test.Test;
import com.pzsp2.coursesteacher.CoursesTeachers;
import com.pzsp2.question.Question;
import com.pzsp2.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode
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

    @Basic
    @JsonIgnore
    @Column(name = "TEACH_PASSWORD")
    public String getPassword() {
        return password;
    }

    @OneToMany(mappedBy = "teacher")
    public Collection<CoursesTeachers> getCoursesTeachers() {
        return coursesTeachers;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    public Collection<Question> getQuestions() {
        return questions;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "teacher")
    public Collection<Test> getTests() {
        return tests;
    }

}

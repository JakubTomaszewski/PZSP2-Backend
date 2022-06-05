package com.pzsp2.user.teacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.coursesteacher.CoursesTeachers;
import com.pzsp2.question.Question;
import com.pzsp2.test.Test;
import com.pzsp2.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "TEACHERS", schema = "PZSP04")
public class Teacher extends User {
    private String login;
    private String password;
    private Boolean enabled;
    private String authority;
    private Collection<CoursesTeachers> coursesTeachers;
    private Collection<Question> questions;
    private Collection<Test> tests;

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    @Basic
    @JsonIgnore
    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    @Basic
    @Column(name = "ENABLED")
    public Boolean getEnabled() {
        return enabled;
    }

    @Basic
    @Column(name = "AUTHORITY")
    public String getAuthority() {
        return authority;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "teacher")
    public Collection<CoursesTeachers> getCoursesTeachers() {
        return coursesTeachers;
    }

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    public Collection<Question> getQuestions() {
        return questions;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "teacher")
    public Collection<Test> getTests() {
        return tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Teacher teacher = (Teacher) o;
        return getUserUserId() != null && Objects.equals(getUserUserId(), teacher.getUserUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

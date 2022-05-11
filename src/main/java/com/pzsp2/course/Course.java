package com.pzsp2.course;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pzsp2.coursesteacher.CoursesTeachers;
import com.pzsp2.question.Question;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COURSES", schema = "PZSP04")
public class Course {
    private String courseCode;
    private String name;
    private Collection<CoursesTeachers> coursesTeachers;
    private Collection<Question> questions;

    @Id
    @Column(name = "COURSE_CODE")
    public String getCourseCode() {
        return courseCode;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode) && Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, name);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    public Collection<CoursesTeachers> getCoursesTeachers() {
        return coursesTeachers;
    }

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    public Collection<Question> getQuestions() {
        return questions;
    }
}

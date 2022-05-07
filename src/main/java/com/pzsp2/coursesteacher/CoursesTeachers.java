package com.pzsp2.coursesteacher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pzsp2.teacher.Teacher;
import com.pzsp2.course.Course;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "COURSES_TEACHERS", schema = "PZSP04")
@IdClass(CoursesTeachersPK.class)
public class CoursesTeachers {
    private String courseCode;
    private Long userId;
    private Course course;
    private Teacher teacher;

    @Id
    @Column(name = "COURSE_CODE")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Id
    @Column(name = "USER_ID")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesTeachers that = (CoursesTeachers) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, userId);
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "COURSE_CODE", referencedColumnName = "COURSE_CODE", nullable = false,
            insertable = false, updatable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_USER_ID", nullable = false,
            insertable = false, updatable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

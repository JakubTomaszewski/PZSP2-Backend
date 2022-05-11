package com.pzsp2.coursesteacher;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Setter
public class CoursesTeachersPK implements Serializable {
    private String courseCode;
    private Long userId;

    @Column(name = "COURSE_CODE")
    @Id
    public String getCourseCode() {
        return courseCode;
    }

    @Column(name = "USER_ID")
    @Id
    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesTeachersPK that = (CoursesTeachersPK) o;
        return Objects.equals(courseCode, that.courseCode) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, userId);
    }
}

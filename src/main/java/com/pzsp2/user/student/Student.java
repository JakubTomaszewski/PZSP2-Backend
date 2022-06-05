package com.pzsp2.user.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pzsp2.solution.Solution;
import com.pzsp2.user.User;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Entity
@Setter
@Table(name = "STUDENTS", schema = "PZSP04")
public class Student extends User {
    private Collection<Solution> solutions;
    private String idNumber;

    public Student(String firstName, String surname, String idNumber) {
        super(firstName, surname, User.STUDENT_TYPE);
        this.idNumber = idNumber;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "student")
    public Collection<Solution> getSolutions() {
        return solutions;
    }

    @Basic
    @Column(name = "STUDENT_ID_NO", length = 6, columnDefinition = "char")
    public String getIdNumber() {
        return idNumber;
    }
}

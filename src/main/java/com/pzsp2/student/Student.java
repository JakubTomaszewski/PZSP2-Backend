package com.pzsp2.student;

import com.pzsp2.solution.Solution;
import com.pzsp2.user.User;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Setter
@Table(name = "STUDENTS", schema = "PZSP04")
public class Student extends User {
    private Collection<Solution> solutions;

    @OneToMany(mappedBy = "student")
    public Collection<Solution> getSolutions() {
        return solutions;
    }
}

package com.pzsp2.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS", schema = "PZSP04")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public static final String TEACHER_TYPE = "t";
    public static final String STUDENT_TYPE = "s";
    private Long userUserId;
    private String userLastName;
    private String userFirstName;
    private String userType;

    public User(String userFirstName, String userLastName, String userType) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userType = userType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_USER_ID")
    public Long getUserUserId() {
        return userUserId;
    }

    @Basic
    @Column(name = "USER_LAST_NAME")
    public String getUserLastName() {
        return userLastName;
    }

    @Basic
    @Column(name = "USER_FIRST_NAME")
    public String getUserFirstName() {
        return userFirstName;
    }

    @Basic
    @Column(name = "USER_TYPE")
    public String getUserType() {
        return userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userUserId, user.userUserId)
                && Objects.equals(userLastName, user.userLastName)
                && Objects.equals(userFirstName, user.userFirstName)
                && Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUserId, userLastName, userFirstName, userType);
    }
}

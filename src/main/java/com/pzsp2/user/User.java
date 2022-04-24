package com.pzsp2.user;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS", schema = "PZSP04")
public class User {
    private Long userUserId;
    private String userLastName;
    private String userFirstName;
    private String userType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_USER_ID")
    public Long getUserUserId() {
        return userUserId;
    }

    public void setUserUserId(Long userUserId) {
        this.userUserId = userUserId;
    }

    @Basic
    @Column(name = "USER_LAST_NAME")
    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    @Basic
    @Column(name = "USER_FIRST_NAME")
    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    @Basic
    @Column(name = "USER_TYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userUserId, user.userUserId) && Objects.equals(userLastName, user.userLastName) && Objects.equals(userFirstName, user.userFirstName) && Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUserId, userLastName, userFirstName, userType);
    }
}

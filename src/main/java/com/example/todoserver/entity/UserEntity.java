package com.example.todoserver.entity;


import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)

    private String lastName;
    @Column(name = "USER_NAME", nullable = false, unique = true)

    private String userName;
    @Column(name = "PASSWORD", nullable = false)

    private String password;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRoleEntity> userRoles;

    @Transient
    public Set<RoleEntity> getRoles() {
        Set<RoleEntity> roles = new HashSet<>();
        for (UserRoleEntity userRole : userRoles) {
            roles.add(userRole.getRole());
        }
        return roles;
    }

    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

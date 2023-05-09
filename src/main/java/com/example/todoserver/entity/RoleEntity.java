package com.example.todoserver.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "ROLES")
public class RoleEntity {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRoleEntity> userRoles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}

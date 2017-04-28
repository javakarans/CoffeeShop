package com.coffeeshop.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mohammad on 4/28/2017.
 */
@Entity
@Table
public class Admin implements Serializable {

    private long adminId;
    private String username;
    private String password;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true , nullable = false)
    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

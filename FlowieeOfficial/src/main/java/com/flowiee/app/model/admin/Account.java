package com.flowiee.app.model.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Account")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int ID;
    private String username;
    private String password;
    private String name;
    private boolean gender;
    private String phone;
    private String email;
    private boolean isAdmin;
    private String avatar;
    private String notes;
    private boolean status;

    public Account(String username, String password, String name, boolean gender, String phone, String email,
                   boolean isAdmin, String avatar, String notes, boolean status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
        this.avatar = avatar;
        this.notes = notes;
        this.status = status;
    }
}

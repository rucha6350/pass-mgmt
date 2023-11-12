package com.rucha.passmgmtservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Passwords")
public class PasswordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "PasswordName")
    private String passwordName;

    @Column(name = "PasswordStored")
    private String passwordStored;

    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "CategoryID")
    private Integer categoryId;
}

package com.rucha.passmgmtservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer id;

    @Column(name = "UserName")
    private String userName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Mobile")
    private String mobile;

    @Column(name = "Password")
    private String password;
}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User implements java.io.Serializable {
    private static final long serialVersionUID=5L;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="position", nullable = false)
    private String position;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;
}

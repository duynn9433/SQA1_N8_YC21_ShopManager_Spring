package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "client")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {
    private static final long serialVersionUID=3L;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

}

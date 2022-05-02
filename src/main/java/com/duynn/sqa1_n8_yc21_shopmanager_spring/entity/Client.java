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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (ID != client.ID) return false;
        if (isActive != client.isActive) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (address != null ? !address.equals(client.address) : client.address != null) return false;
        return phoneNumber != null ? phoneNumber.equals(client.phoneNumber) : client.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }
}

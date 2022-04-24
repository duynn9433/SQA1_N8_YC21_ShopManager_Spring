package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "goods")
public class Goods implements java.io.Serializable {
    private static final long serialVersionUID=4L;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="unity", nullable = false)
    private String unity;
    
    @Column(name="price_per_unit", nullable = false)
    private long pricePerUnit;
    
    @Column(name="description")
    private String description;

    @Column(name="is_active", nullable = false)
    private boolean isActive;
}

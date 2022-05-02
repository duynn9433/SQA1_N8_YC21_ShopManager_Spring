package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "goods")
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goods goods = (Goods) o;

//        if (ID != goods.ID) return false;
        if (pricePerUnit != goods.pricePerUnit) return false;
        if (isActive != goods.isActive) return false;
        if (name != null ? !name.equals(goods.name) : goods.name != null) return false;
        if (unity != null ? !unity.equals(goods.unity) : goods.unity != null) return false;
        return description != null ? description.equals(goods.description) : goods.description == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (unity != null ? unity.hashCode() : 0);
        result = 31 * result + (int) (pricePerUnit ^ (pricePerUnit >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        return result;
    }
}

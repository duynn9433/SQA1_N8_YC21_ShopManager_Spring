package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "buying_goods")
@NoArgsConstructor
@AllArgsConstructor
public class BuyingGoods implements java.io.Serializable {
    private static final long serialVersionUID=2L;
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="id")
    private int ID;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "price_per_unit", nullable = false)
    private long pricePerUnit;

    @Transient
    private long totalPrice;

    @Column(name = "note", nullable = true)
    private String note;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "goods_id", referencedColumnName = "id", nullable = false)
    private Goods goods;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    public  void reCalTotalPrice() {
        this.totalPrice = this.amount * this.pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyingGoods that = (BuyingGoods) o;

//        if (ID != that.ID) return false;
        if (amount != that.amount) return false;
        if (pricePerUnit != that.pricePerUnit) return false;
//        if (totalPrice != that.totalPrice) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        return (goods != null ? goods.equals(that.goods) : that.goods == null);

    }

}

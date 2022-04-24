package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "bill")
public class Bill implements java.io.Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "payment_date",nullable = false)
    private LocalDateTime paymentDate;

    @Transient
    private long paymentTotal;

    @Column(name = "sale_off",nullable = false, columnDefinition = "float default 0")
    private float saleOff;

    @Column(name = "note")
    private String note;

    @Column(name = "is_paid",nullable = false, columnDefinition = "boolean default false")
    private boolean isPaid;

    @Column(name = "is_active",nullable = false, columnDefinition = "boolean default true")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BuyingGoods> buyingGoodsList;

    public Bill() {
        buyingGoodsList = new ArrayList<>();
        isActive = true;
        isPaid = false;
    }

    public void reCalPaymentTotal() {
        paymentTotal =0;
        for (BuyingGoods buyingGoods:buyingGoodsList) {
            buyingGoods.reCalTotalPrice();
            paymentTotal+=buyingGoods.getTotalPrice();
        }
    }
    public void addBuyingGoods(BuyingGoods buyingGoods) throws Exception {
        for (int i =0 ;i<buyingGoodsList.size();i++) {
            BuyingGoods b =buyingGoodsList.get(i);
            if(b.getGoods().getName().equals(buyingGoods.getGoods().getName())){
                long temp = (long)b.getAmount()+buyingGoods.getAmount();
                if(temp> Integer.MAX_VALUE){
                    throw new Exception("Số lượng quá lớn");
                }
                b.setAmount(b.getAmount()+buyingGoods.getAmount());
                b.setTotalPrice(b.getPricePerUnit()*b.getAmount());
                return;
            }
        }
        buyingGoodsList.add(buyingGoods);
    }
}

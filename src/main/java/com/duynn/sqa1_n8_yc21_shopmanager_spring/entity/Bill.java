package com.duynn.sqa1_n8_yc21_shopmanager_spring.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "bill")
public class Bill implements java.io.Serializable, Cloneable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Bill(int id, LocalDateTime paymentDate, long paymentTotal, float saleOff, String note, boolean isPaid, boolean isActive, User user, Client client, List<BuyingGoods> buyingGoodsList) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.paymentTotal = paymentTotal;
        this.saleOff = saleOff;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
        this.user = user;
        this.client = client;
        this.buyingGoodsList = buyingGoodsList;
    }

    public Bill(int id, float saleOff, String note, boolean isPaid, boolean isActive) {
        this.id = id;
        this.saleOff = saleOff;
        this.note = note;
        this.isPaid = isPaid;
        this.isActive = isActive;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

//        if (id != bill.id) return false;
//        if (paymentTotal != bill.paymentTotal) return false;
        if (Float.compare(bill.saleOff, saleOff) != 0) return false;
        if (isPaid != bill.isPaid) return false;
        if (isActive != bill.isActive) return false;
        if (paymentDate != null ? !paymentDate.equals(bill.paymentDate) : bill.paymentDate != null) return false;
        if (note != null ? !note.equals(bill.note) : bill.note != null) return false;
        if (user != null ? !user.equals(bill.user) : bill.user != null) return false;
        if (client != null ? !client.equals(bill.client) : bill.client != null) return false;
        return buyingGoodsList != null ? buyingGoodsList.equals(bill.buyingGoodsList) : bill.buyingGoodsList == null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

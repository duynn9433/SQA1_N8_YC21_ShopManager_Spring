package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BuyingGoodsRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BuyingGoodsServiceTest {
    @Autowired
    BuyingGoodsService buyingGoodsService;

    @Autowired
    BuyingGoodsRepository buyingGoodsRepository;

    @Autowired
    GoodsService goodsService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        Goods g = goodsService.searchByName("bi").get(0);
        Bill bill = new Bill();
        bill.setId(1);
        //note: buying good autoincrease id
        BuyingGoods b = new BuyingGoods(0,1,10000,10000,"",g,bill);
        b = buyingGoodsService.create(b);
        //check save success object
        BuyingGoods b1 = buyingGoodsRepository.findByID(b.getID());
        assertEquals(b,b1);
    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {
//        List<BuyingGoods> list = buyingGoodsService.findAll();
//        Bill bill = list.get(0);
//        bill.setSaleOff(0.2f);
//        bill.setNote("ccccccccccc");
//        bill.setActive(true);
//        bill.setPaid(true);
//        billService.update(bill);
//        List<Bill> bills = billService.findAll();
//        assertNotEquals(0,bills.size());
//        int count =0;
//        for (Bill b:
//                bills) {
//            if (b.getId() == bill.getId() && b.getSaleOff()==bill.getSaleOff() && b.getNote().equals(bill.getNote()) && b.isActive()==bill.isActive() && b.isPaid()==bill.isPaid()){
//                count++;
//            }
//        }
//        assertEquals(1,count);
        Goods goods = new Goods();
        goods.setID(1);
        Bill bill = new Bill();
        bill.setId(1);
        BuyingGoods buyingGoods = new BuyingGoods(2,2,20000,"test",goods,bill);
        buyingGoods = buyingGoodsService.update(buyingGoods);

        List<BuyingGoods> list = buyingGoodsRepository.getBuyingGoodsByBillId(1);
        int count=0;
        for (BuyingGoods b:
             list) {
                if (b.getBill().getId() == buyingGoods.getBill().getId()){
                    count++;
                }
        }


        assertEquals(2,count);


    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {
        List<Goods> list = goodsService.searchByName("coca");
        buyingGoodsService.delete(list.get(0).getID());
        List<Goods> goodsList = goodsService.searchByName(list.get(0).getName());
        int count =0;
        for (Goods g:
                goodsList) {
            if(g.getID() == list.get(0).getID()){
                count++;
            }
        }
        assertEquals(0,count);
    }

    @Test
    void testDelete() {
    }
}
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
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void testDelete() {
    }
}
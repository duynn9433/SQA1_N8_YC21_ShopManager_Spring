package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GoodsServiceTest {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    GoodsService goodsService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByName() {
    }

    @Test
    @DisplayName("Search goods by name No record")
    void searchByName1() {
        List<Goods> list = goodsService.searchByName("0");
        assertEquals(0, list.size());
    }
    @Test
    @DisplayName("Search goods by name Active false true")
    void searchByName2() {
        List<Goods> list = goodsService.searchByName("1");
        Goods goods = new Goods(5,"11","11",11,"test active true",true);
        assertEquals(1, list.size());
        assertEquals(goods, list.get(0));
    }
//    @Test
//    @DisplayName("Search goods by name Active true")
//    void searchByName3() {
//        List<Goods> list = goodsService.searchByName("1");
//        Goods goods = new Goods(5,"11","11",11,"test active true",true);
//        assertEquals(1, list.size());
//        assertEquals(goods, list.get(0));
//    }
}
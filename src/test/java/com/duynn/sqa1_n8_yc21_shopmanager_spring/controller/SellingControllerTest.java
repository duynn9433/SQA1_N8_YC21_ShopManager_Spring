package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class SellingControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
//        sellingController = new SellingController();
//        billService = new BillService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchGoods() throws Exception {
        List<Goods> list = new ArrayList<>();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.getParameter("goodsName")).thenReturn("bi");
//
//        String url=sellingController.searchGoods("bi",request);
//        assertEquals("/selling/searchGoods",url);
//        mockMvc.perform(get("/selling/search-goods"))
//                .andExpect(status().isOk())
//                .andExpect(request().attribute("goodsName","bi"))
//                .andExpect(request().attribute("goodsList",list));

    }

    @Test
    void addGoods() {
    }

    @Test
    void updateGoods() {
    }

    @Test
    void removeGoods() {
    }

    @Test
    void searchClient() {
    }

    @Test
    void addClient() {
    }

    @Test
    void saveBill() {
    }

    @Test
    void testSaveBill() {
    }

    @Test
    void cancelBill() {
    }
}
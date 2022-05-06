package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BillControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    BillRepository billRepository;

    List<Bill> bills;
    Bill bill;
    BillService billService;

    @BeforeEach
    void setUp(){
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User();
        user.setID(2);
        Client client = new Client();
        client.setID(4);

        bill = new Bill(1,localDateTime,(float) 0.3,"huytamtest",false,true,user,client);
        bills = new ArrayList<>();
        bills.add(bill);

    }

    @AfterEach

    void tearDown() {
    }



    @Test
    @DisplayName("khong co khach hang")
    void search() throws Exception {
        bills = new ArrayList<>();

        this.mockMvc.perform(post("/bill/search")
                        .param("search_id","1"))
                .andDo(print())
//                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("listBill", bills))
                .andExpect(view().name("manager/ManagementBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementBillView.jsp"));

    }

    @Test
    @DisplayName("delete bill successfully")
    void delete() throws Exception{

        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User();
        user.setID(2);
        Client client = new Client();
        client.setID(4);

        bill = new Bill(4,localDateTime,(float) 0.3,"huytamtest",false,true,user,client);
        bills = new ArrayList<>();
        bills.add(bill);

        billService = new BillService();
        bill = bills.get(0);

        this.mockMvc.perform(post("/bill/delete")
                        .param("delete_id",String.valueOf(bill.getId())))
                .andDo(print())
                .andExpect(request().sessionAttribute("listBill", bills))
                .andExpect(view().name("manager/ManagementBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementBillView.jsp"));
    }
}
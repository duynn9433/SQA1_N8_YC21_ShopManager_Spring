package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ManagerHomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void home() throws Exception {
        this.mockMvc.perform(get("/manager/home"))
                .andDo(print())
                .andExpect(view().name("manager/ManagerHomeView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagerHomeView.jsp"));
    }

    @Test
    void client() throws Exception {
        this.mockMvc.perform(get("/manager/client"))
                .andDo(print())
                .andExpect(view().name("manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    void bill() throws Exception {
        this.mockMvc.perform(get("/manager/bill"))
                .andDo(print())
                .andExpect(view().name("manager/ManagementBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementBillView.jsp"));
    }
}
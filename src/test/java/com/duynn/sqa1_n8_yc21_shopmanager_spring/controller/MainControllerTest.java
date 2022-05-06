package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class MainControllerTest {
    @Autowired
    MockMvc mockMvc;

    private User user;
    @BeforeEach
    void setUp() {
        user = new User(1, "duy", "123456","duy","manager","0966215413",true);
    }

    @Test
    @DisplayName("Not login")
    void index() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"));
    }
    @Test
    @DisplayName("Manager logined")
    void index2() throws Exception {
        this.mockMvc.perform(get("/").sessionAttr("user", user))
                .andDo(print())
                .andExpect(view().name("manager/ManagerHomeView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagerHomeView.jsp"));
    }
    @Test
    @DisplayName("Seller logined")
    void index3() throws Exception {
        user.setPosition("seller");
        this.mockMvc.perform(get("/").sessionAttr("user",user))
                .andDo(print())
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }
}
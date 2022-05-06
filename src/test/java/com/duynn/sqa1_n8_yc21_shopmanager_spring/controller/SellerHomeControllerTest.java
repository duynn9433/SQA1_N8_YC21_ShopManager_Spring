package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

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
class SellerHomeControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Start add client no return_view")
    void addClient() throws Exception {
        this.mockMvc.perform(get("/seller/add-client")
                        .param("return_view",""))

                .andDo(print())
                .andExpect(view().name("client/AddClient"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/client/AddClient.jsp"));
    }
    @Test
    @DisplayName("Start add client have return_view")
    void addClient2() throws Exception {
        this.mockMvc.perform(get("/seller/add-client")
                        .param("return_view","selling/SellingHome"))

                .andDo(print())
                .andExpect(request().sessionAttribute("return_view","selling/SellingHome"))
                .andExpect(view().name("client/AddClient"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/client/AddClient.jsp"));
    }

    @Test
    void sellingHome() throws Exception {
        this.mockMvc.perform(get("/seller/selling")
                        .param("return_view","selling/SellingHome"))

                .andDo(print())
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    void sellerHome() throws Exception {
        this.mockMvc.perform(get("/seller/home")
                        .param("return_view","selling/SellingHome"))

                .andDo(print())
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }
}
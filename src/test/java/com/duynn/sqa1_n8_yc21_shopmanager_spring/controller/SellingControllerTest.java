package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SellingControllerTest {

    @Autowired
    MockMvc mockMvc;

    List<Goods> list;
    Bill bill;

    @BeforeEach
    void setUp() throws Exception {
        list = new ArrayList<>();
        list.add(new Goods(1,"bia", "lon", 20000, null,true));
        list.add(new Goods(2,"bim bim", "goi", 5000, null,true));

        bill = new Bill();
        Goods goods = list.get(0);
        BuyingGoods buyingGoods = new BuyingGoods();
        buyingGoods.setGoods(goods);
        buyingGoods.setAmount(1);
        buyingGoods.setPricePerUnit(goods.getPricePerUnit());
        buyingGoods.setTotalPrice(1*goods.getPricePerUnit());
        buyingGoods.setBill(bill);
        bill.addBuyingGoods(buyingGoods);
        bill.reCalPaymentTotal();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("SearchGoods: Khong tim thay san pham")
    void searchGoods() throws Exception {
        List<Goods> list = new ArrayList<>();
        String error = "Không tìm thấy sản phẩm nào;";
        this.mockMvc.perform(post("/selling/search-goods")
                        .param("goodsname","sadfasdfsadf"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(request().sessionAttribute("goodsList", list))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("SearchGoods: Tim thay san pham")
    void searchGoods2() throws Exception {
        this.mockMvc.perform(post("/selling/search-goods")
                        .param("goodsname","bi"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("goodsList", list))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("AddGoods: Chua chon san pham")
    void addGoods() throws Exception {
        String error = "Chưa chọn sản phẩm;";
        this.mockMvc.perform(post("/selling/add-goods")
                        .param("amount","1")
                        .param("chooseIndex","")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
//                .andExpect(request().sessionAttribute("goodsList", is(nullValue())))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("AddGoods: Chua chon san pham 2")
    void addGoods2() throws Exception {
        String error = "Chưa chọn sản phẩm;";
        this.mockMvc.perform(post("/selling/add-goods")
                        .param("amount","1")
                        .param("chooseIndex","ádasdasd")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
//                .andExpect(request().sessionAttribute("goodsList", is(nullValue())))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("AddGoods: So luong khong hop le")
    void addGoods3() throws Exception {
        String error ="Số lượng không hợp lệ;";
        this.mockMvc.perform(post("/selling/add-goods")
                        .param("amount","ádasd")
                        .param("chooseIndex","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
//                .andExpect(request().sessionAttribute("goodsList", is(nullValue())))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("AddGoods: Them thanh cong")
    void addGoods4() throws Exception {
        this.mockMvc.perform(post("/selling/add-goods")
                        .param("amount","1")
                        .param("chooseIndex","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(bill)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    void thu(){
        Bill bill = new Bill();
        Bill bill2 = new Bill();
        Goods goods = new Goods(1,"bia", "lon", 20000, null,true);
        Goods goods1 = new Goods(1,"bia", "lon", 20000, null,true);
        BuyingGoods buyingGoods = new BuyingGoods(1,1,1,1,"",goods,bill);
        BuyingGoods buyingGoods2 = new BuyingGoods(1,1,1,1,"",goods1,bill);
        assertEquals(goods,goods1);
        assertEquals(buyingGoods,buyingGoods2);
    }


    @Test
    @DisplayName("UpdateGoods: Sua thanh cong")
    void updateGoods() throws Exception {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods(1,"bia", "lon", 20000, null,true));
        list.add(new Goods(2,"bim bim", "goi", 5000, null,true));

        Bill bill = new Bill();
        Goods goods = list.get(0);
        BuyingGoods buyingGoods = new BuyingGoods();
        buyingGoods.setGoods(goods);
        buyingGoods.setAmount(10);  //amount after change
        buyingGoods.setPricePerUnit(goods.getPricePerUnit());
        buyingGoods.setTotalPrice(1*goods.getPricePerUnit());
        buyingGoods.setBill(bill);
        bill.addBuyingGoods(buyingGoods);
        bill.reCalPaymentTotal();


        this.mockMvc.perform(post("/selling/update-goods")
                        .param("amount","10")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("UpdateGoods: So luong khong hop le")
    void updateGoods2() throws Exception {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods(1,"bia", "lon", 20000, null,true));
        list.add(new Goods(2,"bim bim", "goi", 5000, null,true));

        Bill bill = new Bill();
        Goods goods = list.get(0);
        BuyingGoods buyingGoods = new BuyingGoods();
        buyingGoods.setGoods(goods);
        buyingGoods.setAmount(10);  //amount change
        buyingGoods.setPricePerUnit(goods.getPricePerUnit());
        buyingGoods.setTotalPrice(1*goods.getPricePerUnit());
        buyingGoods.setBill(bill);
        bill.addBuyingGoods(buyingGoods);
        bill.reCalPaymentTotal();

        String error = "Số lượng không hợp lệ;";
        this.mockMvc.perform(post("/selling/update-goods")
                        .param("amount","asdasdasd")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("RemoveGoods: Chua chon san pham")
    void removeGoods() throws Exception {

        String error = "Chưa chọn sản phẩm;";
        this.mockMvc.perform(post("/selling/remove-goods")
                        .param("index","")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("RemoveGoods: Xoa thanh cong")
    void removeGoods2() throws Exception {
        Bill bill2 = new Bill();

        String error = "Chưa chọn sản phẩm;";
//        Bill billAfterRemove = (Bill) bill.clone();
//        billAfterRemove.getBuyingGoodsList().remove(0);
        this.mockMvc.perform(post("/selling/remove-goods")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(bill2)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
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
package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.*;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
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
    @Autowired
    BillService billService;

    List<Bill> bills;
    Bill bill;


    @BeforeEach
    void setUp(){
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User();
        user.setID(2);
        Client client = new Client();
        client.setID(4);

        bill = new Bill(8,(float) 0.5,"GGG",false,true,user,client);
        bills = new ArrayList<>();
        bills.add(bill);

    }

    @AfterEach

    void tearDown() {
    }

    @Test
    @DisplayName("khong co bill")
    void search() throws Exception {
        bills = new ArrayList<>();

        this.mockMvc.perform(post("/bill/search")
                        .param("search_id","5"))
                .andDo(print())
//                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("listBill", bills))
                .andExpect(view().name("manager/ManagementBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementBillView.jsp"));

    }

    @Test
    @DisplayName(" co bill")
    void search1() throws Exception {
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
    @Transactional
    @Rollback
    void delete() throws Exception{
//        User user = new User();
//        user.setID(2);
//        Client client = new Client();
//        client.setID(4);
//
//        bill = new Bill(8,(float) 0.5,"GGG",false,true,user,client);
//        bills = new ArrayList<>();
//        bills.add(bill);
//        bill = new Bill();


//        billService = new BillService();
        bills = new ArrayList<>();
        List<Bill> bills= billService.search(8);

        bill = bills.get(0);


        this.mockMvc.perform(post("/bill/delete")
                        .param("id",String.valueOf(bill.getId())))
                .andDo(print())
                .andExpect(request().sessionAttribute("listBill", bills))
                .andExpect(view().name("manager/ManagementBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementBillView.jsp"));
    }

    //duynn
    @Test
    void editBill() throws Exception {
        Bill bill = new Bill();
        bill.setPaymentTotal(5000);
        List<Bill> listBill = new ArrayList<>();
        listBill.add(bill);

        this.mockMvc.perform(post("/bill/edit-bill")
                        .param("index","1")
                        .sessionAttr("listBill",listBill))
                .andDo(print())
                .andExpect(request().sessionAttribute("bill", bill))
                .andExpect(view().name("manager/EditBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditBillView.jsp"));
    }


    //duynn
    @Test
    @DisplayName("UpdateGoods: Sua thanh cong")
    void updateGoods1() throws Exception {
        List<Goods> list = new ArrayList<>();
        list.add(new Goods(1,"bia", "lon", 20000, null,true));
        list.add(new Goods(2,"bim bim", "goi", 5000, null,true));

        Bill bill = new Bill();
        Goods goods = list.get(0);
        BuyingGoods buyingGoods = new BuyingGoods();
        buyingGoods.setGoods(goods);
        buyingGoods.setAmount(1);  //amount after change
        buyingGoods.setPricePerUnit(goods.getPricePerUnit());
        buyingGoods.setTotalPrice(1*goods.getPricePerUnit());
        buyingGoods.setBill(bill);
        bill.addBuyingGoods(buyingGoods);
        bill.reCalPaymentTotal();

        Bill expectedBill = new Bill();
        Goods expectedGoods = list.get(0);
        BuyingGoods expectedBuyingGoods = new BuyingGoods();
        expectedBuyingGoods.setGoods(expectedGoods);
        expectedBuyingGoods.setAmount(10);  //amount after change
        expectedBuyingGoods.setPricePerUnit(goods.getPricePerUnit());
        expectedBuyingGoods.setTotalPrice(1*goods.getPricePerUnit());
        expectedBuyingGoods.setBill(expectedBill);
        expectedBill.addBuyingGoods(expectedBuyingGoods);
        expectedBill.reCalPaymentTotal();

        this.mockMvc.perform(post("/bill/update-goods")
                        .param("amount","10")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(expectedBill)))
                .andExpect(view().name("manager/EditBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditBillView.jsp"));
    }
    //duynn
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
        this.mockMvc.perform(post("/bill/update-goods")
                        .param("amount","asdasdasd")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", new Bill()))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("manager/EditBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditBillView.jsp"));
    }

    //duynn
    @Test
    @DisplayName("RemoveGoods: Chua chon san pham")
    void removeGoods1() throws Exception {

        String error = "Chưa chọn sản phẩm;";
        this.mockMvc.perform(post("/bill/remove-goods")
                        .param("index","")
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("manager/EditBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditBillView.jsp"));
    }
    //duynn
    @Test
    @DisplayName("RemoveGoods: Xoa thanh cong")
    void removeGoods2() throws Exception {
        List<Goods> list = new ArrayList<>();
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
        Bill bill2 = new Bill();

        String error = "Chưa chọn sản phẩm;";
//        Bill billAfterRemove = (Bill) bill.clone();
//        billAfterRemove.getBuyingGoodsList().remove(0);
        this.mockMvc.perform(post("/bill/remove-goods")
                        .param("index","1")
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(bill2)))
                .andExpect(view().name("manager/EditBillView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditBillView.jsp"));
    }

    @Test
    void save() {
    }
}
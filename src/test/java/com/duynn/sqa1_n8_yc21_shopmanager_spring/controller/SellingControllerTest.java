package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.*;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    BillRepository billRepository;

    List<Goods> list;
    Bill bill;
    List<Client> listClient;

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

        listClient = new ArrayList<>();
        listClient.add(new Client(1,"Nguyễn Ngọc Duy","hanoi", "0966215413",true));
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
    @DisplayName("UpdateGoods: Sua thanh cong")
    void updateGoods() throws Exception {
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

        this.mockMvc.perform(post("/selling/update-goods")
                        .param("amount","10")
                        .param("index","1")
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(expectedBill)))
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
    @DisplayName("SearchClient: Khong tim thay khach hang")
    void searchClient() throws Exception {
        String error = "Không tìm thấy khách hàng nào;";
        this.mockMvc.perform(post("/selling/search-client")
                        .param("client_phone","1234567879"))

                .andDo(print())
//                .andExpect(request().sessionAttribute("bill", is()))
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("SearchClient: Tim thay khach hang")
    void searchClient2() throws Exception {
//        List<Client> list = new ArrayList<>();
//        list.add(new Client(1,"Nguyễn Ngọc Duy","hanoi", "0966215413",true));
        this.mockMvc.perform(post("/selling/search-client")
                        .param("client_phone","0966215413"))

                .andDo(print())
                .andExpect(request().sessionAttribute("listClient", is(listClient)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("AddClient: Chua chon khach hang")
    void addClient() throws Exception {
        String error = "Chưa chọn khách hàng;";
        this.mockMvc.perform(post("/selling/add-client")
                        .param("chooseIndex",""))

                .andDo(print())
//                .andExpect(request().sessionAttribute("bill", is()))
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("AddClient: Them khach hang thanh cong")
    void addClient2() throws Exception {
        Bill bill = new Bill();
        Bill billAfterAdd = new Bill();
        billAfterAdd.setClient(listClient.get(0));
        this.mockMvc.perform(post("/selling/add-client")
                        .param("chooseIndex","1")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(billAfterAdd)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("ConfirmBill: Xac nhan thanh cong")
    void saveBill() throws Exception {
        bill.setClient(listClient.get(0));
//        bill.setSaleOff(0.5f);

        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","0.5")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(bill)))
                .andExpect(view().name("selling/Confirm"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/Confirm.jsp"));
    }

    @Test
    @DisplayName("ConfirmBill: Giam gia khong hop le sai format")
    void saveBill2() throws Exception {
        bill.setClient(listClient.get(0));
//        bill.setSaleOff(0.5f);
        String error = "Giảm giá không hợp lệ;";
        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","adfasdf")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("ConfirmBill: Giam gia khong hop le <0")
    void saveBill3() throws Exception {
        bill.setClient(listClient.get(0));
//        bill.setSaleOff(0.5f);
        String error = "Giảm giá không hợp lệ (chỉ trong khoảng 0-1);";
        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","-0.5")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("ConfirmBill: Giam gia khong hop le >1")
    void saveBill4() throws Exception {
        bill.setClient(listClient.get(0));
//        bill.setSaleOff(0.5f);
        String error = "Giảm giá không hợp lệ (chỉ trong khoảng 0-1);";
        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","1.5")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("ConfirmBill: Thieu Client")
    void saveBill5() throws Exception {
//        bill.setClient(listClient.get(0));
//        bill.setSaleOff(0.5f);
        String error = "Vui lòng thêm khách hàng;";
        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","0.5")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }
    @Test
    @DisplayName("ConfirmBill: Thieu mat hang")
    void saveBill6() throws Exception {
        bill.setClient(listClient.get(0));
        bill.getBuyingGoodsList().clear();
//        bill.setSaleOff(0.5f);
        String error = "Vui lòng thêm sản phẩm;";
        this.mockMvc.perform(get("/selling/confirm-bill")
                        .param("sale_off","0.5")
                        .sessionAttr("listClient", listClient)
                        .sessionAttr("goodsList", list)
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("selling/SellingHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/selling/SellingHome.jsp"));
    }

    @Test
    @DisplayName("SaveBill")
    @Transactional
    @Rollback
    void testSaveBill() throws Exception {
        Bill bill = new Bill(0,LocalDateTime.now(),0,0.5f,null,true,true
                ,new User(1, "", "", "", "", "", true)
                ,listClient.get(0), new ArrayList<>());
        Goods goods = new Goods(1,"bia","lon",20000,null,true);
        BuyingGoods buyingGoods = new BuyingGoods(0,1,20000,0,null,goods,bill);
        bill.addBuyingGoods(buyingGoods);

        this.mockMvc.perform(post("/selling/save-bill")
                        .sessionAttr("user", new User(1,"","","","","",true))
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().attribute("bill", is(bill)))
                .andExpect(request().sessionAttribute("confirmBillMsg", is("Lưu thành công")))
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }
    @Test
    @DisplayName("SaveBill: loi")
    @Transactional
    @Rollback
    void testSaveBill2() throws Exception {
        Bill bill = new Bill(0,null,0,0.5f,null,true,true
                ,new User(1, "", "", "", "", "", true)
                ,listClient.get(0), new ArrayList<>());
        Goods goods = new Goods(1,"bia","lon",20000,null,true);
        BuyingGoods buyingGoods = new BuyingGoods(0,1,20000,0,null,goods,bill);
        bill.addBuyingGoods(buyingGoods);

        String confirmBillMsg = "Lưu không thành công, lỗi cơ sở dữ liệu;";

        this.mockMvc.perform(post("/selling/save-bill")
                        .sessionAttr("user", new User(1,"","","","","",true))
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("confirmBillMsg", is(confirmBillMsg)))
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }

    @Test
    void cancelBill() throws Exception {
        this.mockMvc.perform(post("/selling/cancel-bill")
                        .sessionAttr("bill", bill))

                .andDo(print())
                .andExpect(request().sessionAttribute("bill", is(nullValue())))
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }
}
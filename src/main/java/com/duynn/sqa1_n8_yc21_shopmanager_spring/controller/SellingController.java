package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.*;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.ClientService;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/selling")
public class SellingController {
    private String url;
    private String error;
    @Autowired
    GoodsService goodsService;
    @Autowired
    ClientService clientService;
    @Autowired
    BillService billService;


    @PostMapping("/search-goods")
    public String searchGoods(@RequestParam("goodsname") String goodsName, HttpServletRequest request) {
        error = "";
        List<Goods> goodsList = goodsService.searchByName(goodsName);
        if(goodsList.size()<=0){
            error += "Không tìm thấy sản phẩm nào;";
        }
        request.getSession().setAttribute("error",error);
        request.getSession().setAttribute("goodsList",goodsList);
        url="selling/SellingHome";
        return url;
    }

    @PostMapping("/add-goods")
    public String addGoods(@Nullable @RequestParam("amount") String sAmount,
                           @Nullable @RequestParam("chooseIndex") String sChooseIndex, HttpServletRequest request) {
        error = "";
        Bill bill = (Bill) request.getSession().getAttribute("bill");
        List<Goods> goodsList = (List<Goods>) request.getSession().getAttribute("goodsList");
        int count = 0;
        try {
            int amount = Integer.parseInt(sAmount);
            if(amount<=0){
                throw new NumberFormatException();
            }
            count++;
            Goods goods = goodsList.get(Integer.parseInt(sChooseIndex)-1);
            BuyingGoods buyingGoods = new BuyingGoods();
            buyingGoods.setGoods(goods);
            buyingGoods.setAmount(amount);
            buyingGoods.setPricePerUnit(goods.getPricePerUnit());
            buyingGoods.setTotalPrice(amount*goods.getPricePerUnit());
            buyingGoods.setBill(bill);

            bill.addBuyingGoods(buyingGoods);
            bill.reCalPaymentTotal();
        }catch (NullPointerException e){
            error += "Chưa chọn sản phẩm;";
        }catch (NumberFormatException e){
            if(count==0){
                error += "Số lượng không hợp lệ;";
            }else if(count==1){
                error += "Chưa chọn sản phẩm;";
            }
        } catch (Exception e) {
            error += e.getMessage();
        }
        request.getSession().setAttribute("error",error);
        request.getSession().setAttribute("bill",bill);
        url="selling/SellingHome";
        return url;
    }
    @PostMapping("/update-goods")
    public String updateGoods(@RequestParam("amount") String sAmount,
                           @RequestParam("index") String sIndex, HttpServletRequest request) {
        error = "";
        Bill bill = (Bill) request.getSession().getAttribute("bill");
        //update hang
        try {
            int amount = Integer.parseInt(sAmount);
            int index = Integer.parseInt(sIndex) -1;
            if(amount <= 0) {
                bill.getBuyingGoodsList().remove(index);
            }else {
                bill.getBuyingGoodsList().get(index).setAmount(amount);
            }

            //update tong tien bill
            bill.reCalPaymentTotal();

            request.getSession().setAttribute("bill",bill);
        }catch (NumberFormatException e){
            error += "Số lượng không hợp lệ;";
        }
        request.getSession().setAttribute("error",error);
        url="selling/SellingHome";
        return url;
    }
    @PostMapping("/remove-goods")
    public String removeGoods(@RequestParam("index") String sIndex,HttpServletRequest request) {
        error = "";
        Bill bill = (Bill) request.getSession().getAttribute("bill");
        try {
            int index = Integer.parseInt(sIndex) -1 ;
            bill.getBuyingGoodsList().remove(index);
            bill.reCalPaymentTotal();

            request.getSession().setAttribute("bill",bill);
        } catch (NumberFormatException e) {
            error += "Chưa chọn sản phẩm;";
        }
        request.getSession().setAttribute("error",error);
        url="selling/SellingHome";
        return url;
    }

    @PostMapping("/search-client")
    public String searchClient(@RequestParam("client_phone") String clientPhone, HttpServletRequest request) {
        error = "";
        try {
            List<Client> listClient = clientService.searchClientByPhone(clientPhone);
            if(listClient.size()<=0){
                error += "Không tìm thấy khách hàng nào;";
            }
            request.getSession().setAttribute("listClient",listClient);
        } catch (SQLException e) {
            e.printStackTrace();
            error+= "Lỗi kết nối cơ sở dữ liệu;";
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("error",error);
        url="selling/SellingHome";
        return url;
    }

    @PostMapping("/add-client")
    public String addClient(@Nullable @RequestParam("chooseIndex") String chooseIndex, HttpServletRequest request) {
        error = "";
        List<Client> list = (List<Client>) request.getSession().getAttribute("listClient");
        try {
            Client client = list.get(Integer.parseInt(chooseIndex)-1);
            Bill bill = (Bill) request.getSession().getAttribute("bill");
            bill.setClient(client);
        }catch (NumberFormatException e){
            error += "Chưa chọn khách hàng;";
        }
        request.getSession().setAttribute("error",error);
        url="selling/SellingHome";
        return url;
    }

    @GetMapping("/confirm-bill")
    public String saveBill(@RequestParam("sale_off") String sSaleOff, HttpServletRequest request) {
        error = "";
        HttpSession session = request.getSession();
        url="selling/Confirm";
        session.removeAttribute("goodsList");
        session.removeAttribute("listClient");

        Bill bill = (Bill) session.getAttribute("bill");

        try {
            float saleOff = Float.parseFloat(sSaleOff);
            if(saleOff>1 || saleOff<0){
                error += "Giảm giá không hợp lệ (chỉ trong khoảng 0-1);";
                url="selling/SellingHome";
            }else{
                bill.setSaleOff(saleOff);
            }
        }catch (NumberFormatException e){
            bill.setSaleOff(0);
            error += "Giảm giá không hợp lệ;";
            url="selling/SellingHome";
        }
        if(bill.getClient()==null){
            error+= "Vui lòng thêm khách hàng;";
            url="selling/SellingHome";
        }else if(bill.getBuyingGoodsList().size()<=0){
            error+= "Vui lòng thêm sản phẩm;";
            url="selling/SellingHome";
        }

        bill.setPaymentDate(LocalDateTime.now());
        request.getSession().setAttribute("error",error);
        return url;
    }

    @PostMapping("/save-bill")
    public String saveBill(HttpServletRequest request) {
        error = "";
        HttpSession session = request.getSession();
        url="seller/SellerHome";
        User user = (User) session.getAttribute("user");
        Bill bill = (Bill) session.getAttribute("bill");
        bill.setUser(user);
        bill.setPaid(true);
        try {
            Bill res = billService.create(bill);
            request.getSession().setAttribute("confirmBillMsg", "Lưu thành công");
            request.getSession().setAttribute("save_billId",res.getId());
            request.setAttribute("bill",res);
        } catch (Exception e) {
            error+= "";
            request.getSession().setAttribute("confirmBillMsg", "Lưu không thành công, lỗi cơ sở dữ liệu;");
            e.printStackTrace();
        }
        session.removeAttribute("bill");

        return url;
    }
    @PostMapping("/cancel-bill")
    public String cancelBill(HttpServletRequest request) {
        request.getSession().removeAttribute("bill");
        url="seller/SellerHome";
        return url;
    }

}


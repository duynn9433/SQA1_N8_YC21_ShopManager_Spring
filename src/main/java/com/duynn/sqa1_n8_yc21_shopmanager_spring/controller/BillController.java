package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;

    String error;
    String url;

    @PostMapping("/search")
    public String search(@RequestParam("search_id") String searchId, HttpServletRequest request) {
        String id = searchId;
        HttpSession session = request.getSession();
        String url="";
        String error = "";
        if(id!="") {
            try {
                List<Bill> list = billService.search(Integer.parseInt(id));
                session.setAttribute("listBill", list);
                url = "manager/ManagementBillView";
                if(list.size()==0) {
                    error = "Không tìm thấy hóa đơn";
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                error+= "Nhập lại id";
            } catch (Exception e) {
                e.printStackTrace();
                error+= "Lỗi hệ thống";
            }
        } else {
            try {
                List<Bill> list = billService.findAll();
                session.setAttribute("listBill", list);
                url = "manager/ManagementBillView";
            } catch (Exception e) {
                e.printStackTrace();
                error+= e.getMessage();
            }
        }

        session.setAttribute("error", error);
        return url;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id, HttpServletRequest request) {
        String error = "";
        String url = "manager/ManagementBillView";
        try {
            HttpSession session = request.getSession();
            int res = billService.delete(Integer.parseInt(id));
            List<Bill> list = new ArrayList<>();
            session.setAttribute("listBill", list);
            url = "manager/ManagementBillView";
        } catch (SQLException e) {
            e.printStackTrace();
            error+= e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            error+= e.getMessage();
        }
        HttpSession session = request.getSession();
        session.setAttribute("error", error);
        return url;
    }

    @PostMapping("/edit-bill")
    public String editBill(@RequestParam("index") String index, HttpServletRequest request){
        String url = "manager/EditBillView";

        Bill bill = ((List<Bill>)request.getSession().getAttribute("listBill"))
                .get(Integer.parseInt(index)-1);
        request.getSession().setAttribute("bill", bill);
        request.getSession().setAttribute("error",error);

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
    @PostMapping("/save")
    public String save(@RequestParam("paymentDate") String paymentDate,
                       @RequestParam("saleOff") String saleOff,
                       @RequestParam("note") String note, HttpServletRequest request){
        url = "manager/EditBillView";
        error = "";
        try{
            Bill bill = (Bill) request.getSession().getAttribute("bill");
            bill.setPaymentDate(LocalDateTime.parse(paymentDate));
            bill.setNote(note);
            bill.setSaleOff(Float.parseFloat(saleOff));
            if(Float.parseFloat(saleOff) > 1 && Float.parseFloat(saleOff) <0){
                throw new NumberFormatException();
            }
            billService.update(bill);
        }catch(NumberFormatException nfe){
            error+="Nhập lại giảm giá";
            url="manager/EditBillView";
        }catch (Exception e){
            error+="Lưu không thành công";
            url="manager/EditBillView";
        }
        request.getSession().setAttribute("error",error);
        return url;
    }

}

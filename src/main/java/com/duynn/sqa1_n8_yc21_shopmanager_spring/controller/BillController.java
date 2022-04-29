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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;

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


}

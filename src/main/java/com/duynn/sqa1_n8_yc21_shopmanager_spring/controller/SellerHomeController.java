package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/seller")
public class SellerHomeController {
    @GetMapping("/add-client")
    public String addClient(HttpServletRequest request) {
        request.setAttribute("client", new Client());
        return "client/AddClient";
    }

    @GetMapping("/selling")
    public String sellingHome(HttpServletRequest request) {
        Bill bill = new Bill();
        bill.setUser((User) request.getSession().getAttribute("user"));
        request.getSession().setAttribute("bill", bill);
        request.getSession().setAttribute("error", "");
        return "selling/SellingHome";
    }

    @GetMapping("/home")
    public String sellerHome(HttpServletRequest request) {
        return "seller/SellerHome";
    }
}

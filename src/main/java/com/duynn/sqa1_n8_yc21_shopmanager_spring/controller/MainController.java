package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if(user.getPosition().equals("seller")) {
                return "seller/SellerHome";
            }else if(user.getPosition().equals("manager")) {
                return "manager/ManagerHomeView";
            }
        }
        return "index";
    }

}

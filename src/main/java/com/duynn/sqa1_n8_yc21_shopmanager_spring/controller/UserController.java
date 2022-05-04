package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            if(user.getPosition().equals("seller")) {
                return "seller/SellerHome";
            }else if(user.getPosition().equals("manager")) {
                return "manager/ManagerHomeView";
            }
        }
        request.setAttribute("error", "Sai username hoặc password ");
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "index";
    }
}

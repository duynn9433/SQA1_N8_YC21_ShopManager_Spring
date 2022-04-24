package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerHomeController {
    @RequestMapping("/home")
    public String home() {
        return "manager/ManagerHomeView";
    }

    @RequestMapping("/client")
    public String client() {
        return "manager/ManagementClientView";
    }
    @RequestMapping("/bill")
    public String bill() {
        return "manager/ManagementBillView";
    }
}

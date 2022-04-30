package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends Mockito {
    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void managerLogin() throws Exception {
        User user = new User(1, "manager", "manager", "manager", "manager", "0123456789", true);
        this.mockMvc.perform(post("/user/login").param("username","manager")
                        .param("password","manager"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user", is(user)))
//                .andExpect(request().attribute("username", is("manager")))
//                .andExpect(request().attribute("password", is("manager")))
                .andExpect(view().name("manager/ManagerHomeView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagerHomeView.jsp"));
    }
    @Test
    void sellerLogin() throws Exception {
        User user = new User(2, "seller", "seller", "seller", "seller", "0123666666", true);
        this.mockMvc.perform(post("/user/login").param("username","seller")
                        .param("password","seller"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(request().sessionAttribute("user", is(user)))
//                .andExpect(request().attribute("username", is("manager")))
//                .andExpect(request().attribute("password", is("manager")))
                .andExpect(view().name("seller/SellerHome"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/seller/SellerHome.jsp"));
    }

    @Test
    void loginFail() throws Exception {
        this.mockMvc.perform(post("/user/login").param("username","fail")
                        .param("password","fail"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andExpect(request().sessionAttribute("user", is(nullValue())));
    }

}
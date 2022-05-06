package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Find User: Active true")
    void findByUsername() {
        User user = new User(1,"manager","manager","manager","manager","0123456789",true);

        User expected = userService.findByUsername("manager");

        assertEquals(expected, user);

    }
    @Test
    @DisplayName("Find User: Active false")
    void findByUsername2() {
        User user = null;

        User expected = userService.findByUsername("seller2");

        assertEquals(expected, user);

    }
}
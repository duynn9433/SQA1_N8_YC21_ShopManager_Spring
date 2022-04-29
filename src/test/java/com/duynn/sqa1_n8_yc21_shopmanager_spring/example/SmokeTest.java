package com.duynn.sqa1_n8_yc21_shopmanager_spring.example;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.controller.SellingController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    SellingController sellingController;

    @Test
    public void contextLoads() {
        assertThat(sellingController).isNotNull();
    }
}

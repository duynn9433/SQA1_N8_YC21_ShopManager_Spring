package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.*;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BillServiceTest {
    @Autowired
    BillService billService;

    @Autowired
    BillRepository billRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Transactional
    @Rollback
    void create() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        User user = new User();
        user.setID(2);
        Client client = new Client();
        client.setID(4);
        // create constructor but do not use attru
        Bill bill1 = new Bill(4,localDateTime,(float) 0.1,"lau lau",true,true,user,client);
        bill1 = billService.create(bill1);

        List<Bill> bill = billService.findAll();
        assertEquals(1,bill.size());

    }

    @Test
    @Transactional
    @Rollback
    void update() throws Exception {

        List<Bill> list = billService.findAll();

        Bill bill = list.get(0);
        bill.setSaleOff(0.2f);
        bill.setNote("ccccccccccc");
        bill.setActive(true);
        bill.setPaid(false);

        billService.update(bill);

        List<Bill> bills = billService.findAll();
        assertEquals(1,bills.size());

        int count =0;
        for (Bill b: bills) {
            if (b.getId() == bill.getId()){
                count++;
            }
        }
        assertEquals(1,count);
    }

    @Test
    @Transactional
    @Rollback
    void delete() throws Exception {

        List<Bill> list = billService.findAll();
        billService.delete(list.get(0).getId());
        List<Bill> bills = billService.search(list.get(0).getId());
        int count =0;
        for (Bill b:
             bills) {
            if(b.getId() == list.get(0).getId()){
                count++;
            }
        }
        assertEquals(0,count);
    }

    @Test
    @DisplayName("search bill isActive False ")
    void search() throws Exception {
        List<Bill> newn = new ArrayList<>();
        List<Bill> bills = billService.search(2);
        assertEquals(newn.size(), bills.size());
    }


    @Test
    @DisplayName("search bill isActive True ")
    void search2() throws Exception {
        List<Bill> bills = billService.search(3);
        assertEquals(1, bills.size());
        // trong csdl buyinggood có billid là 2 và 3
//        sang csdl ở bill id 2 thì isActive là false
//        hàm search() là tìm dựa trên buyinggoods
    }


    @Test
    @DisplayName("search all isActive True")
    void findAll() throws Exception {

        List<Bill> bills = billService.findAll();
        assertEquals(1, bills.size());
        // trong csdl buyinggood có billid là 2 và 3
//        sang csdl ở bill id 2 thì isActive là false
//        hàm findAll() là tìm dựa trên buyinggoods

    }

}
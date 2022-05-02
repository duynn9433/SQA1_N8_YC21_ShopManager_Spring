package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.*;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
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

        User user = new User();
        user.setID(1);
        Client client = new Client();
        client.setID(2);
        // create constructor but do not use attru
        Bill bill1 = new Bill(0,(float) 0.1,"lau lau",true,true);
        bill1 = billService.create(bill1);
        List<Bill> bill = billRepository.findBillByIdAndIsActiveIsTrueAndIsPaidIsFalse(bill1.getId());
        assertEquals(bill1,bill);

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
        bill.setPaid(true);
        billService.update(bill);
        List<Bill> bills = billService.findAll();
        assertNotEquals(0,bills.size());
        int count =0;
        for (Bill b:
             bills) {
            if (b.getId() == bill.getId() && b.getSaleOff()==bill.getSaleOff() && b.getNote().equals(bill.getNote()) && b.isActive()==bill.isActive() && b.isPaid()==bill.isPaid()){
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
    void search() throws Exception {
        List<Bill> newn = new ArrayList<>();
        List<Bill> bills = billService.search(3);
        assertEquals(newn, bills);
    }

    @Test
    void findAll() throws Exception {
        List<Bill> newn = new ArrayList<>();
        List<Bill> bills = billService.findAll();
        assertEquals(newn, bills);
    }
}
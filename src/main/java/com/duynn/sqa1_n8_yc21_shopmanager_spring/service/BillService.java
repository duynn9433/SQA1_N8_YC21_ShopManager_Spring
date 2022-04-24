package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BillService implements GeneralService<Bill> {

    @Autowired
    BillRepository billRepository;

    @Override
    public Bill create(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    public int delete(Bill bill) throws Exception {
        return billRepository.deleteBill(bill.getId());
    }
}

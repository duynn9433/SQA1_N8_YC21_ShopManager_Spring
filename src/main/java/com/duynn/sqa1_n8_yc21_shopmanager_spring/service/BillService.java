package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BuyingGoodsRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class BillService implements GeneralService<Bill> {

    @Autowired
    BillRepository billRepository;

    @Autowired
    BuyingGoodsRepository buyingGoodsRepository;

    @Override
    public Bill create(Bill bill) throws Exception {
        Logger.getLogger(BillService.class.getName()).info("Create bill" + bill.toString());
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) throws Exception {
        Logger.getLogger(BillService.class.getName()).info("Update bill" + bill.toString());
        return billRepository.save(bill);
    }

    @Override
    public int delete(int id) throws Exception {
        Logger.getLogger(BillService.class.getName()).info("Delete bill" + id);
        return billRepository.deleteBill(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Bill> search(int id) throws Exception {
        List<Bill> list = billRepository.findBillByIdAndIsActiveIsTrueAndIsPaidIsFalse(id);
        for(Bill bill : list) {
            List<BuyingGoods> buyingGoods = buyingGoodsRepository.getBuyingGoodsByBillId(bill.getId());
            bill.setBuyingGoodsList(buyingGoods);
        }
        Logger.getLogger(BillService.class.getName()).info("Search bill" + id + ": " + list.toString());
        return list;
    }

    public List<Bill> findAll() throws Exception {
        List<Bill> list = billRepository.findAllByIsActiveIsTrueAndIsPaidIsFalse();
        for(Bill bill : list) {
            List<BuyingGoods> buyingGoods = buyingGoodsRepository.getBuyingGoodsByBillId(bill.getId());
            bill.setBuyingGoodsList(buyingGoods);
        }
        Logger.getLogger(BillService.class.getName()).info("Find all bill: " + list.toString());
        return list;
    }

}

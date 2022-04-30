package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BillRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BuyingGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BillService implements GeneralService<Bill> {

    @Autowired
    BillRepository billRepository;

    @Autowired
    BuyingGoodsRepository buyingGoodsRepository;

    @Override
    public Bill create(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) throws Exception {
        return billRepository.save(bill);
    }

    @Override
    public int delete(int id) throws Exception {
        return billRepository.deleteBill(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Bill> search(int id) throws Exception {
        List<Bill> list = billRepository.findBillByIdAndIsActiveIsTrueAndIsPaidIsFalse(id);
        for(Bill bill : list) {
            List<BuyingGoods> buyingGoods = buyingGoodsRepository.getBuyingGoodsByBillId(bill.getId());
            bill.setBuyingGoodsList(buyingGoods);
        }
        return list;
    }

    public List<Bill> findAll() throws Exception {
        List<Bill> list = billRepository.findAllByIsActiveIsTrueAndIsPaidIsFalse();
        for(Bill bill : list) {
            List<BuyingGoods> buyingGoods = buyingGoodsRepository.getBuyingGoodsByBillId(bill.getId());
            bill.setBuyingGoodsList(buyingGoods);
        }
        return list;
    }

}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BuyingGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class BuyingGoodsService implements GeneralService<BuyingGoods>{
    @Autowired
    BuyingGoodsRepository buyingGoodsRepository;

    @Override
    public BuyingGoods create(BuyingGoods buyingGoods) throws Exception {
//        buyingGoods.set
        Logger.getLogger(this.getClass().getName()).info("Create BuyingGoods: " + buyingGoods.toString());
        return buyingGoodsRepository.save(buyingGoods);
    }

    @Override
    public BuyingGoods update(BuyingGoods buyingGoods) throws Exception {
        Logger.getLogger(this.getClass().getName()).info("Update BuyingGoods: " + buyingGoods.toString());
        return buyingGoodsRepository.save(buyingGoods);
    }

    @Override
    public int delete(int id) throws Exception {
        Logger.getLogger(this.getClass().getName()).info("Delete BuyingGoods: " + id);
        return buyingGoodsRepository.deleteBuyingGoods(id);
    }

}

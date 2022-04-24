package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.BuyingGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BuyingGoodsService implements GeneralService<BuyingGoods>{
    @Autowired
    BuyingGoodsRepository buyingGoodsRepository;

    @Override
    public BuyingGoods create(BuyingGoods buyingGoods) throws Exception {
//        buyingGoods.set
        return buyingGoodsRepository.save(buyingGoods);
    }

    @Override
    public BuyingGoods update(BuyingGoods buyingGoods) throws Exception {
        return buyingGoodsRepository.save(buyingGoods);
    }

    @Override
    public int delete(BuyingGoods buyingGoods) throws Exception {
        return buyingGoodsRepository.deleteBuyingGoods(buyingGoods.getID());
    }
}

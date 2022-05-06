package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsService{
    @Autowired
    GoodsRepository goodsRepository;

    public List<Goods> searchByName(String name) {
        List<Goods> list = goodsRepository.searchByNameAndIsActiveIsTrue(name);
        Logger.getLogger(GoodsService.class.getName()).info("Search Goods by name: " + list);
        return list;
    }
}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsService{
    @Autowired
    GoodsRepository goodsRepository;

    public List<Goods> findByName(String name) {
        List<Goods> list = goodsRepository.findByNameAndIsActiveIsTrue(name);
        return list;
    }
    public List<Goods> searchByName(String name) {
        List<Goods> list = goodsRepository.searchByNameAndIsActiveIsTrue(name);
        return list;
    }
}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.repository;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.BuyingGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BuyingGoodsRepository extends JpaRepository<BuyingGoods, Integer>, JpaSpecificationExecutor<BuyingGoods> {
    @Modifying
    @Query(value = "update buying_goods set is_active=0 where id=?1",nativeQuery = true)
    int deleteBuyingGoods(Integer id);

    BuyingGoods findByID(Integer id);
}

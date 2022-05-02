package com.duynn.sqa1_n8_yc21_shopmanager_spring.repository;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Integer>, JpaSpecificationExecutor<Goods> {
    ArrayList<Goods> findByNameAndIsActiveIsTrue(String name);

    @Query(value = "select * from goods where upper(name) like concat('%',upper(?1),'%') and is_active = true", nativeQuery = true)
    ArrayList<Goods> searchByNameAndIsActiveIsTrue(String name);
}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.repository;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer>, JpaSpecificationExecutor<Bill> {
    @Modifying
    @Query(value = "update bill set is_active=0 where id=?1",nativeQuery = true)
    int deleteBill(Integer id);

    @Modifying
    @Query(value="insert into bill(customer_id, total_price, is_active) values(?1, ?2, 1)",nativeQuery = true)
    int createBill(Bill bill);

    List<Bill> findBillByIdAndIsActiveIsTrueAndIsPaidIsFalse(Integer id);

    List<Bill> findAllByIsActiveIsTrueAndIsPaidIsFalse();

}

package com.duynn.sqa1_n8_yc21_shopmanager_spring.repository;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client> {

    @Modifying
    @Query(value = "update client set is_active=0 where id=?1", nativeQuery = true)
    int deleteClient(Integer id);

    @Query(value = "select * from client where upper(phone_number) like concat('%',upper(?1),'%') and is_active = true", nativeQuery = true)
    List<Client> searchByNameAndIsActiveIsTrue(String phoneNumber);
}

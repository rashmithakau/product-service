package com.LittleLanka.product_service.repository;

import com.LittleLanka.product_service.entity.PriceUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@EnableJpaRepositories
public interface PriceUpdateRepository extends JpaRepository<PriceUpdate, Long> {
    @Query(value = "select pu.price as price from price_update pu, product p where p.product_id = pu.product_id and pu.update_date = ?1 and p.product_id = ?2", nativeQuery = true)
    Double findPriceUpdateByPriceUpdateDateAndProductId(Date dateObj, Long id);
}

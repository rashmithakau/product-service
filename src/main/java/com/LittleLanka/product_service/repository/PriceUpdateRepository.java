package com.LittleLanka.product_service.repository;

import com.LittleLanka.product_service.dto.queryInterfaces.PriceListInterface;
import com.LittleLanka.product_service.entity.PriceUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface PriceUpdateRepository extends JpaRepository<PriceUpdate, Long> {
    @Query(value = "select pu.price as price from price_update pu, product p where p.product_id = pu.product_id and pu.update_date = ?1 and p.product_id = ?2", nativeQuery = true)
    Double findPriceUpdateByPriceUpdateDateAndProductId(Date dateObj, Long id);

    @Query(value = "SELECT p.product_id, p.product_name, pu.price, pu.update_date FROM product p JOIN price_update pu ON p.product_id = pu.product_id WHERE pu.update_date = (SELECT MAX(update_date) FROM price_update WHERE product_id = pu.product_id AND update_date <= ?1) ORDER BY p.product_id", nativeQuery = true)
    List<PriceListInterface> findProductIdAndPriceByDateEquals(Date date);
}

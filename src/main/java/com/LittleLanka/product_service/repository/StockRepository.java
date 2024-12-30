package com.LittleLanka.product_service.repository;

import com.LittleLanka.product_service.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByOutletIdAndProductProductId(Long outletId, Long productId);
}

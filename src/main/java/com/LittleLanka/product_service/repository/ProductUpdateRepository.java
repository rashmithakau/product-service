package com.LittleLanka.product_service.repository;

import com.LittleLanka.product_service.entity.PriceUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductUpdateRepository extends JpaRepository<PriceUpdate, Long> {
}

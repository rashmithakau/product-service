package com.LittleLanka.product_service.repository;

import com.LittleLanka.product_service.entity.Product;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends CrudRepository<Product, Long> {
}

package com.LittleLanka.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "product_update")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productUpdateId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_update_date", columnDefinition = "DATETIME")
    private Date productUpdateDate;

    @Column(name = "product_update_price")
    private Double productUpdatePrice;

}

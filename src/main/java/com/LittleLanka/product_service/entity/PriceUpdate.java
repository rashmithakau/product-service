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
public class PriceUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_update_id")
    private Long priceUpdateId;

    @Column(name = "update_date", columnDefinition = "DATETIME")
    private Date priceUpdateDate;

    @Column(name = "price")
    private Double priceUpdatePrice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}

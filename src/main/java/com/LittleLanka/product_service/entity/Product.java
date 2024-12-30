package com.LittleLanka.product_service.entity;

import com.LittleLanka.product_service.entity.enums.CategoryType;
import com.LittleLanka.product_service.entity.enums.MeasuringUnitType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private CategoryType productCategory;

    @Column(name = "product_status", columnDefinition = "TINYINT default 0")
    private boolean productStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_type")
    private MeasuringUnitType measuringType = MeasuringUnitType.NUMBER;

    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product")
    private List<PriceUpdate> priceUpdates;

}

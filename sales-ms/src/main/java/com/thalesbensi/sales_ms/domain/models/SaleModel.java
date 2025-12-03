package com.thalesbensi.sales_ms.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sales_tb")
public class SaleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;
    private Integer quantity;

    @Column(name = "unit_value")
    private BigDecimal unitValue;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;


}

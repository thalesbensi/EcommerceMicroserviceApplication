package com.thalesbensi.sales_ms.domain.models;

import com.thalesbensi.sales_ms.api.dtos.Sale.SaleResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sales_tb")
@Data
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

    public SaleResponseDTO toSaleResponseDTO(){
        return new SaleResponseDTO(
                this.id,
                this.productId,
                this.quantity,
                this.unitValue,
                this.totalValue,
                this.createdAt
        );
    }


}

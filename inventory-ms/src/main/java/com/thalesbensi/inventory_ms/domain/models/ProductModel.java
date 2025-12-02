package com.thalesbensi.inventory_ms.domain.models;

import com.thalesbensi.inventory_ms.api.dtos.ProductResponseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products_tb")
@Data
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_At",insertable = false)
    private Date updatedAt;

    public ProductResponseDTO toResponseDTO() {
        return new ProductResponseDTO(
                this.id,
                this.name,
                this.description,
                this.price,
                this.quantity,
                this.createdAt,
                this.updatedAt
        );
    }

}

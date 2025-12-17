package com.thalesbensi.inventory_ms.api.dtos.productDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @NotNull(message = "The Product Name cannot be null")
        String name,

        @NotNull(message = "The Product Description cannot be null")
        String description,

        @Positive(message = "The quantity must be greater than zero")
        @NotNull(message = "The Product Price cannot be null")
        BigDecimal price,

        @Positive(message = "The quantity must be greater than zero")
        @NotNull(message = "The Product Quantity cannot be null")
        Integer quantity){}

package com.thalesbensi.sales_ms.api.dtos.Sale;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleRequestDTO(
        @NotNull(message = "The Product ID cannot be null")
        Long productId,

        @Positive(message = "The quantity must be greater than zero")
        Integer quantity
) {}

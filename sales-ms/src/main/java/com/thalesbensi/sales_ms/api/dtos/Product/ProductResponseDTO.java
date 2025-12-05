package com.thalesbensi.sales_ms.api.dtos.Product;

import java.math.BigDecimal;
import java.util.Date;

public record ProductResponseDTO(Long id, String name, String description, BigDecimal price, Integer quantity, Date createdAt, Date updatedAt) { }


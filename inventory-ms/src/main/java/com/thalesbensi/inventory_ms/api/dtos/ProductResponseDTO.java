package com.thalesbensi.inventory_ms.api.dtos;

import java.math.BigDecimal;
import java.util.Date;

public record ProductResponseDTO(Long id, String name, String description, BigDecimal price, Integer quantity, Date createdAt, Date updatedAt) { }

package com.thalesbensi.inventory_ms.api.dtos;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, String description, BigDecimal price, Integer quantity){}

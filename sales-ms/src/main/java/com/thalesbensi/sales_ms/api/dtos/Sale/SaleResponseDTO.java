package com.thalesbensi.sales_ms.api.dtos.Sale;

import java.math.BigDecimal;
import java.util.Date;

public record SaleResponseDTO(Long id, Long productId, Integer quantity, BigDecimal unitValue, BigDecimal totalValue, Date createdAt) {
}

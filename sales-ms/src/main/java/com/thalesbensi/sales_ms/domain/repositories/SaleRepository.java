package com.thalesbensi.sales_ms.domain.repositories;

import com.thalesbensi.sales_ms.domain.models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleModel, Long> {
}

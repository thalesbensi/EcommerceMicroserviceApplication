package com.thalesbensi.inventory_ms.domain.repositories;

import com.thalesbensi.inventory_ms.domain.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel,Long> {
}

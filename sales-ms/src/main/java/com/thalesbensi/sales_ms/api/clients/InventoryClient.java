package com.thalesbensi.sales_ms.api.clients;

import com.thalesbensi.sales_ms.api.dtos.Product.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "inventory-ms", url = "http://localhost:8082/products")
public interface InventoryClient {

    @GetMapping("/{id}")
    ProductResponseDTO findById(@PathVariable Long id);
}

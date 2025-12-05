package com.thalesbensi.sales_ms.api.clients;

import com.thalesbensi.sales_ms.api.dtos.Product.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "inventory-ms", url = "http://localhost:8082/api/products")
public interface InventoryClient {

    @PostMapping("/validate-stock/{id}")
    Boolean validateStock(@PathVariable Long id, Integer quantity);

    @GetMapping("/{id}")
    ProductResponseDTO findById(@PathVariable Long id);
}

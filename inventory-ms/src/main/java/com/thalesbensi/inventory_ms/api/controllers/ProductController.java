package com.thalesbensi.inventory_ms.api.controllers;

import com.thalesbensi.inventory_ms.api.dtos.productDtos.ProductRequestDTO;
import com.thalesbensi.inventory_ms.api.dtos.productDtos.ProductResponseDTO;
import com.thalesbensi.inventory_ms.domain.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Valid ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> create(@Valid ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@Valid @PathVariable Long id, ProductRequestDTO productRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

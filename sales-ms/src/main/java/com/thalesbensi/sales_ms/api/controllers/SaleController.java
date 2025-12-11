package com.thalesbensi.sales_ms.api.controllers;

import com.thalesbensi.sales_ms.api.dtos.Sale.SaleRequestDTO;
import com.thalesbensi.sales_ms.api.dtos.Sale.SaleResponseDTO;
import com.thalesbensi.sales_ms.domain.services.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping()
    public ResponseEntity<List<SaleResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(saleService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<SaleResponseDTO> create(@Valid @RequestBody SaleRequestDTO saleRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(saleRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

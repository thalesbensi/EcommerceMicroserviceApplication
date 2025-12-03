package com.thalesbensi.sales_ms.domain.services;

import com.thalesbensi.sales_ms.api.dtos.SaleResponseDTO;
import com.thalesbensi.sales_ms.domain.models.SaleModel;
import com.thalesbensi.sales_ms.domain.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }


    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll().stream()
                .map(sale -> new SaleResponseDTO(
                        sale.getId(),
                        sale.getProductId(),
                        sale.getQuantity(),
                        sale.getUnitValue(),
                        sale.getTotalValue(),
                        sale.getCreatedAt()
                ))
                .toList();
    }

    public SaleResponseDTO findById(Long id) {
        SaleModel saleModel = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        return saleModel.toSaleResponseDTO();
    }

    public SaleResponseDTO create() {

    }

    public void delete(Long id) {
        SaleModel saleModel = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        saleRepository.delete(saleModel);
    }
}

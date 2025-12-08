package com.thalesbensi.sales_ms.domain.services;

import com.thalesbensi.sales_ms.api.clients.InventoryClient;
import com.thalesbensi.sales_ms.api.dtos.Product.ProductResponseDTO;
import com.thalesbensi.sales_ms.api.dtos.Sale.SaleRequestDTO;
import com.thalesbensi.sales_ms.api.dtos.Sale.SaleResponseDTO;
import com.thalesbensi.sales_ms.api.producers.SaleProducer;
import com.thalesbensi.sales_ms.domain.models.SaleModel;
import com.thalesbensi.sales_ms.domain.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final InventoryClient inventoryClient;
    private final SaleProducer saleProducer;

    public SaleService(SaleRepository saleRepository, InventoryClient inventoryClient, SaleProducer saleProducer) {
        this.saleRepository = saleRepository;
        this.inventoryClient = inventoryClient;
        this.saleProducer = saleProducer;
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


    public SaleResponseDTO create(SaleRequestDTO saleRequestDTO) {

        ProductResponseDTO product = inventoryClient.findById(saleRequestDTO.productId());
        if (product == null) {
            throw new RuntimeException("Product with id: " + saleRequestDTO.productId() + " not found");
        }

        if (product.quantity() < saleRequestDTO.quantity()) {
            throw new RuntimeException("Insufficient Stock for product with id: " + saleRequestDTO.productId());
        }

        SaleModel saleModel = new SaleModel();
        saleModel.setProductId(saleRequestDTO.productId());
        saleModel.setQuantity(saleRequestDTO.quantity());
        saleModel.setUnitValue(product.price());

        BigDecimal total = product.price()
                .multiply(BigDecimal.valueOf(saleRequestDTO.quantity()));
        saleModel.setTotalValue(total);

        SaleModel savedSale = saleRepository.save(saleModel);
        saleProducer.publishSaleOnQueue(saleRequestDTO);

        return savedSale.toSaleResponseDTO();
    }


    public void delete(Long id) {
        SaleModel saleModel = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        saleRepository.delete(saleModel);
    }
}

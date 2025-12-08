package com.thalesbensi.inventory_ms.domain.services;

import com.thalesbensi.inventory_ms.api.dtos.ProductRequestDTO;
import com.thalesbensi.inventory_ms.api.dtos.ProductResponseDTO;
import com.thalesbensi.inventory_ms.domain.models.ProductModel;
import com.thalesbensi.inventory_ms.domain.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProductService {

    final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getCreatedAt(),
                        product.getUpdatedAt()
                ))
                .toList();
    }


    public ProductResponseDTO findById(@Valid Long id) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
      return product.toResponseDTO();
    }

    public ProductResponseDTO create(@Valid ProductRequestDTO productRequestDTO) {
        ProductModel productModel = new ProductModel();
        productModel.setName(productRequestDTO.name());
        productModel.setDescription(productRequestDTO.description());
        productModel.setPrice(productRequestDTO.price());
        productModel.setQuantity(productRequestDTO.quantity());

        ProductModel savedProduct = productRepository.save(productModel);
        return savedProduct.toResponseDTO();
    }

    public ProductResponseDTO update(@Valid Long id, ProductRequestDTO productRequestDTO) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productModel.setName(productRequestDTO.name());
        productModel.setDescription(productRequestDTO.description());
        productModel.setPrice(productRequestDTO.price());
        productModel.setQuantity(productRequestDTO.quantity());

        ProductModel updatedProduct = productRepository.save(productModel);
        return updatedProduct.toResponseDTO();
    }

    public void delete(@Valid Long id) {
        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(productModel);
    }
}

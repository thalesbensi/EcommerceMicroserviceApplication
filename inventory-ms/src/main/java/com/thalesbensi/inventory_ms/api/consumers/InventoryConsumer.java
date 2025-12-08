package com.thalesbensi.inventory_ms.api.consumers;

import com.thalesbensi.inventory_ms.api.dtos.salesDtos.SaleRequestDTO;
import com.thalesbensi.inventory_ms.domain.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryConsumer {

     final ProductRepository productRepository;

    public InventoryConsumer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = "${broker.queue.inventory.name}")
    public void listenEmailQueueAndUpdateStock(SaleRequestDTO saleRequestDTO) {
        var productOptional = productRepository.findById(saleRequestDTO.productId());
        if (productOptional.isPresent()) {
            var product = productOptional.get();
            int newQuantity = product.getQuantity() - saleRequestDTO.quantity();
            product.setQuantity(newQuantity);
            productRepository.save(product);
        } else {
            log.error("Product with id {} not found in Inventory", saleRequestDTO.productId());
        }
        log.info("Sale with id {} and quantity {} received in Inventory and updated in stock", saleRequestDTO.productId(), saleRequestDTO.quantity());
    }
}

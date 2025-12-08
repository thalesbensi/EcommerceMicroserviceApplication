package com.thalesbensi.sales_ms.api.producers;

import com.thalesbensi.sales_ms.api.dtos.Sale.SaleRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SaleProducer {

    final RabbitTemplate rabbitTemplate;

    public SaleProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.inventory.name}")
    private String routingKey;

    public void publishSaleOnQueue(SaleRequestDTO saleRequestDTO) {
        log.info("Sending sale with productId {} and quantity {} to inventory queue", saleRequestDTO.productId(), saleRequestDTO.quantity());
        rabbitTemplate.convertAndSend(routingKey, saleRequestDTO);
    }
}

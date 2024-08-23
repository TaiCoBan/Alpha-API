package me.project.alphaapi.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import me.project.alphaapi.constants.Constant;
import me.project.alphaapi.dto.ProductDTO;
import me.project.alphaapi.entity.Product;
import me.project.alphaapi.exception.NotFoundException;
import me.project.alphaapi.mapper.ProductMapper;
import me.project.alphaapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    @KafkaListener(topics = Constant.PRODUCT_TOPIC, groupId = Constant.GROUP_ID)
    public void consume(@Payload Product product, @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        switch (key) {
            case Constant.SAVE:
                log.info("Save product received -> {}", product);
                productRepo.save(product);
                break;
            case Constant.UPDATE:
                log.info("Update product received -> {}", product);
                Product current = productRepo.findById(product.getProductId())
                        .orElseThrow(() -> new NotFoundException("Product not found ID = " + product.getProductId()));

                current.setName(product.getName());
                current.setDescription(product.getDescription());
                current.setCategory(product.getCategory());
                current.setPrice(product.getPrice());
                current.setSKU(product.getSKU());
                current.setStock(product.getStock());

                productRepo.save(current);
                break;
            case Constant.DELETE:
                log.info("Delete product received -> {}", product);
                productRepo.delete(product);
                break;
            default:
                log.warn("Unknown key received -> {}", key);
                break;
        }
    }
}

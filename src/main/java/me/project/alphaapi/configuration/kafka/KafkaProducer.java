package me.project.alphaapi.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import me.project.alphaapi.constants.Constant;
import me.project.alphaapi.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendSaveMessage(Product product) {
        log.info("Message sent -> {}", product);

        Message<Product> message = MessageBuilder
                .withPayload(product)
                .setHeader(KafkaHeaders.TOPIC, Constant.PRODUCT_TOPIC)
                .setHeader(KafkaHeaders.KEY, Constant.SAVE)
                .build();

        kafkaTemplate.send(message);
    }


    public void sendUpdateMessage(Product product){
        log.info("Message sent -> {}", product);
        kafkaTemplate.send(Constant.PRODUCT_TOPIC, Constant.UPDATE, product);
    }

    public void sendDeleteMessage(Product product){
        log.info("Message sent -> {}", product);
        kafkaTemplate.send(Constant.PRODUCT_TOPIC, Constant.DELETE, product);
    }
}

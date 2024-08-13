package me.project.alphaapi.configuration.kafka;

import me.project.alphaapi.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
class KafkaSenderExample {

    private final Logger LOG = LoggerFactory.getLogger(KafkaSenderExample.class);

    private KafkaTemplate<String, String> kafkaTemplate;
    private RoutingKafkaTemplate routingKafkaTemplate;
    private KafkaTemplate<String, ProductDTO> userKafkaTemplate;

    @Autowired
    KafkaSenderExample(KafkaTemplate<String, String> kafkaTemplate, RoutingKafkaTemplate routingKafkaTemplate,
                       KafkaTemplate<String, ProductDTO> userKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.routingKafkaTemplate = routingKafkaTemplate;
        this.userKafkaTemplate = userKafkaTemplate;
    }

    void sendMessage(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("--------------------------------");

        kafkaTemplate.send(topicName, message);
    }

    void sendWithRoutingTemplate(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("--------------------------------");

        routingKafkaTemplate.send(topicName, message.getBytes());
    }

    void sendCustomMessage(ProductDTO productDTO, String topicName) {
        LOG.info("Sending Json Serializer : {}", productDTO);
        LOG.info("--------------------------------");

        userKafkaTemplate.send(topicName, productDTO);
    }

    void sendMessageWithCallback(String message, String topicName) {
        LOG.info("Sending : {}", message);
        LOG.info("---------------------------------");

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOG.info("Success Callback: [{}] delivered with offset -{}", message,
                        result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.warn("Failure Callback: Unable to deliver message [{}]. {}", message, ex.getMessage());
            }
        });
    }

}

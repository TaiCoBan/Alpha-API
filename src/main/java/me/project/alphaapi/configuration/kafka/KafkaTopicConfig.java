package me.project.alphaapi.configuration.kafka;

import me.project.alphaapi.constants.Constant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic productTopic() {
        return TopicBuilder.name(Constant.PRODUCT_TOPIC)
                .partitions(3)
                .build();
    }
}
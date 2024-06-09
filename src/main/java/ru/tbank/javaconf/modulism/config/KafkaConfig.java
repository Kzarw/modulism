package ru.tbank.javaconf.modulism.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class KafkaConfig {

  @Bean
  public NewTopic operationsIncomingTopic() {
    return new NewTopic("operations.topic", 1, (short) 1);
  }

  @Bean
  public NewTopic reportsTopic() {
    return new NewTopic("reports.tax", 1, (short) 1);
  }
}

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class Publisher {

  //language=JSON
  private static final String SAMPLE_JSON = """
      {
        "eventType" : "CREATED",
        "operation" : {
          "payerBank": "SomeBank",
          "payerAccount": "200030",
          "payeeAccount": "123456",
          "payeeBank": "T-Банк",
          "amount": 30000,
          "transactionNumber": 944404321,
          "operationDateTime": "2024-06-01T11:12:13Z"
        }
      }
    """;

  public static void main(String[] args) throws Exception {
    var configs = Map.<String, Object>ofEntries(
      Map.entry(CommonClientConfigs.CLIENT_ID_CONFIG, "test"),
      Map.entry(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"),
      Map.entry(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class),
      Map.entry(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
    );

    try (var producer = new KafkaProducer<String, String>(configs)) {
      producer.send(new ProducerRecord<>("operations.topic", SAMPLE_JSON));
    }
  }
}
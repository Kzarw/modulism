package ru.tbank.javaconf.modulism.module.operations.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.tracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import ru.tbank.javaconf.modulism.module.operations.dto.event.CreateOperationEvent;
import ru.tbank.javaconf.modulism.module.operations.dto.event.DeleteOperationEvent;
import ru.tbank.javaconf.modulism.module.operations.dto.event.OperationEvent;
import ru.tbank.javaconf.modulism.module.operations.service.OperationService;

@Component
@Slf4j
@RequiredArgsConstructor
public class OperationKafkaListener {

  private final ObjectMapper objectMapper;
  private final OperationService operationService;
  private final Tracer tracer;

  @KafkaListener(
    topics = "operations.topic",
    groupId = "modulismApp"
  )
  public void onOperationEventReceived(Message<String> operationEventMessage) throws JsonProcessingException {
    var span = tracer.nextSpan().name("OperationKafkaListener");
    try (final var ws = tracer.withSpan(span.start())) {
      log.debug("Received operations message [{}]", operationEventMessage.getPayload());
      final OperationEvent operationEvent = objectMapper.readValue(operationEventMessage.getPayload(), OperationEvent.class);
      switch (operationEvent.getEventType()) {
        case CREATED -> operationService.createOperation(((CreateOperationEvent) operationEvent).getOperation());
        case DELETED ->
          operationService.deleteOperation(((DeleteOperationEvent) operationEvent).getTransactionNumber());
      }
    } finally {
      span.end();
    }
  }
}

package ru.tbank.javaconf.modulism.operations.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.javaconf.modulism.operations.dto.event.CreateOperationEvent;
import ru.tbank.javaconf.modulism.operations.dto.event.DeleteOperationEvent;
import ru.tbank.javaconf.modulism.operations.dto.event.OperationEvent;
import ru.tbank.javaconf.modulism.operations.service.InternalOperationService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OperationKafkaListener {

  private final ObjectMapper objectMapper;
  private final InternalOperationService operationService;

  @KafkaListener(
    topics = "operations.topic",
    groupId = "modulismApp"
  )
  @Transactional
  public void onOperationEventReceived(Message<String> operationEventMessage) throws JsonProcessingException {
    log.debug("Received operations message [{}]", operationEventMessage.getPayload());
    final OperationEvent operationEvent = objectMapper.readValue(operationEventMessage.getPayload(), OperationEvent.class);
    switch (operationEvent.getEventType()) {
      case CREATED -> operationService.createOperation(((CreateOperationEvent) operationEvent).getOperation());
      case DELETED -> operationService.deleteOperation(((DeleteOperationEvent) operationEvent).getTransactionNumber());
    }
  }
}

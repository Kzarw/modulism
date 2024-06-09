package ru.tbank.javaconf.modulism.dto.event;

import lombok.Getter;
import lombok.Setter;
import ru.tbank.javaconf.modulism.dto.OperationEventType;

@Getter
@Setter
public final class DeleteOperationEvent extends OperationEvent {
  private String transactionNumber;

  public DeleteOperationEvent() {
    super(OperationEventType.DELETED);
  }
}

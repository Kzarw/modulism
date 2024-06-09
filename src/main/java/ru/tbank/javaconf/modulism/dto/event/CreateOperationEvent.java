package ru.tbank.javaconf.modulism.dto.event;


import lombok.Getter;
import lombok.Setter;
import ru.tbank.javaconf.modulism.dto.OperationEventType;

@Getter
@Setter
public final class CreateOperationEvent extends OperationEvent {

  private CreateOperationDto operation;

  public CreateOperationEvent() {
    super(OperationEventType.CREATED);
  }
}

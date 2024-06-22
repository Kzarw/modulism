package ru.tbank.javaconf.modulism.operations.dto.event;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tbank.javaconf.modulism.operations.dto.OperationEventType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSubTypes(
  value = {
    @JsonSubTypes.Type(value = CreateOperationEvent.class, name = "CREATED"),
    @JsonSubTypes.Type(value = DeleteOperationEvent.class, name = "DELETED")
  }
)
@JsonTypeInfo(property = "eventType", use = JsonTypeInfo.Id.NAME)
public class OperationEvent {
  private OperationEventType eventType;
}

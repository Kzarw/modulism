package ru.tbank.javaconf.modulism.mapper;

import org.springframework.stereotype.Component;
import ru.tbank.javaconf.modulism.dto.event.CreateOperationDto;
import ru.tbank.javaconf.modulism.dto.OperationDto;
import ru.tbank.javaconf.modulism.entity.Operation;

import java.time.ZoneId;

@Component
public class OperationMapper {

  public static final ZoneId MSK_TZ = ZoneId.of("Europe/Moscow");

  // Convert Operation to OperationDto
  public OperationDto toDto(Operation operation) {
    return new OperationDto(
      operation.getId(),
      operation.getPayerBank(),
      operation.getPayeeBank(),
      operation.getPayerAccount(),
      operation.getPayeeAccount(),
      operation.getAmount(),
      operation.getDatetime().atZone(MSK_TZ).toLocalDate()
    );
  }

  // Convert OperationDto to Operation
  public Operation toEntity(CreateOperationDto operationDto) {
    Operation operation = new Operation();
    operation.setPayerBank(operationDto.payerBank());
    operation.setPayeeBank(operationDto.payeeBank());
    operation.setPayerAccount(operationDto.payerAccount());
    operation.setPayeeAccount(operationDto.payeeAccount());
    operation.setAmount(operationDto.amount());
    operation.setDatetime(operationDto.operationDateTime());
    return operation;
  }
}

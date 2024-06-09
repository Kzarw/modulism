package ru.tbank.javaconf.modulism.module.operations.dto.event;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CreateOperationDto(
  String payerBank,
  String payeeBank,
  String payerAccount,
  String payeeAccount,
  BigDecimal amount,
  String transactionNumber,
  Instant operationDateTime,
  UUID paymentId
) {
}

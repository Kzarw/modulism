package ru.tbank.javaconf.modulism.module.operations.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OperationDto(
  long id,
  String payerBank,
  String payeeBank,
  String payerAccount,
  String payeeAccount,
  BigDecimal amount,
  LocalDate date
) {
}

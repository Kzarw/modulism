package ru.tbank.javaconf.modulism.operations.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OperationDto {
  long id();

  String payerBank();

  String payeeBank();

  String payerAccount();

  String payeeAccount();

  BigDecimal amount();

  LocalDate date();
}

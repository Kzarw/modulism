package ru.tbank.javaconf.modulism.operations.api.model;

import java.time.LocalDate;

public record CreatedOperationEvent(
  String account,
  LocalDate date
) {
}

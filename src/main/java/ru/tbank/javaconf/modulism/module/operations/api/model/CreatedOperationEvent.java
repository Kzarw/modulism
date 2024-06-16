package ru.tbank.javaconf.modulism.module.operations.api.model;

import java.time.LocalDate;

public record CreatedOperationEvent(
  String account,
  LocalDate date
) {
}

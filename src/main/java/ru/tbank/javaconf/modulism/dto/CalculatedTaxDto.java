package ru.tbank.javaconf.modulism.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CalculatedTaxDto(
  Instant calculatedAt,
  BigDecimal amountToPay,
  int year,
  String account
) {
}

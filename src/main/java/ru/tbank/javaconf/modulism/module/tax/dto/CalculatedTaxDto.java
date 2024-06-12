package ru.tbank.javaconf.modulism.module.tax.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CalculatedTaxDto(
  Instant calculatedAt,
  BigDecimal amountToPay,
  int year,
  String account
) implements ru.tbank.javaconf.modulism.module.tax.api.model.CalculatedTaxDto {
}

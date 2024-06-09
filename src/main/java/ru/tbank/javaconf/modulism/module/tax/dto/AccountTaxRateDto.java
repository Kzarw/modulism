package ru.tbank.javaconf.modulism.module.tax.dto;

import java.math.BigDecimal;

public record AccountTaxRateDto(
  String account,
  int year,
  BigDecimal rate
) {
}

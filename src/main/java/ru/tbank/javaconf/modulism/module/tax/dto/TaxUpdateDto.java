package ru.tbank.javaconf.modulism.module.tax.dto;

import java.math.BigDecimal;

public record TaxUpdateDto(
  BigDecimal taxRate
) {
}

package ru.tbank.javaconf.modulism.module.tax.dto;

import java.math.BigDecimal;

public record TaxesDto(
  BigDecimal amountToPay
) {
}

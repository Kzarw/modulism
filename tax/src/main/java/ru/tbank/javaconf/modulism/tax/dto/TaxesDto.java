package ru.tbank.javaconf.modulism.tax.dto;

import java.math.BigDecimal;

public record TaxesDto(
  BigDecimal amountToPay
) {
}

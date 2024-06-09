package ru.tbank.javaconf.modulism.dto;

import java.math.BigDecimal;

public record TaxesDto(
  BigDecimal amountToPay
) {
}

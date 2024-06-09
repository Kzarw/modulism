package ru.tbank.javaconf.modulism.dto;

import java.math.BigDecimal;

public record TaxInfo(
  BigDecimal amount
) {
}

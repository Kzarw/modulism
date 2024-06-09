package ru.tbank.javaconf.modulism.module.tax.dto;

import java.math.BigDecimal;

public record TaxInfo(
  BigDecimal amount
) {
}

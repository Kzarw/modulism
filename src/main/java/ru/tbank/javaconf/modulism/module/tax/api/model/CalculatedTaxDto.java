package ru.tbank.javaconf.modulism.module.tax.api.model;

import java.math.BigDecimal;
import java.time.Instant;

public interface CalculatedTaxDto {
  Instant calculatedAt();

  BigDecimal amountToPay();

  int year();

  String account();
}
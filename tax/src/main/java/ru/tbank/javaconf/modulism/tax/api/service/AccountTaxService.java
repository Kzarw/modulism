package ru.tbank.javaconf.modulism.tax.api.service;

import ru.tbank.javaconf.modulism.tax.api.model.CalculatedTaxDto;

public interface AccountTaxService {
  CalculatedTaxDto calculateTax(String account, Integer year);
}

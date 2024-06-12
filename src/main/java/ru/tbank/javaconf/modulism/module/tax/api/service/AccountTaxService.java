package ru.tbank.javaconf.modulism.module.tax.api.service;

import ru.tbank.javaconf.modulism.module.tax.api.model.CalculatedTaxDto;

public interface AccountTaxService {
  CalculatedTaxDto calculateTax(String account, Integer year);
}

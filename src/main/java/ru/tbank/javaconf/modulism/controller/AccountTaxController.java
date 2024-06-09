package ru.tbank.javaconf.modulism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.javaconf.modulism.dto.CalculatedTaxDto;
import ru.tbank.javaconf.modulism.service.AccountTaxService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account-taxes")
public class AccountTaxController {

  private final AccountTaxService accountTaxService;

  @GetMapping("/{account}/{year}/calculate")
  public CalculatedTaxDto calculateTax(@PathVariable String account, @PathVariable Integer year) {
    return accountTaxService.calculateTax(account, year);
  }
}

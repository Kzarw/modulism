package ru.tbank.javaconf.modulism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tbank.javaconf.modulism.dto.AccountTaxRateDto;
import ru.tbank.javaconf.modulism.dto.TaxUpdateDto;
import ru.tbank.javaconf.modulism.service.AccountTaxRateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account-tax-rates")
public class AccountTaxRateController {

  private final AccountTaxRateService accountTaxRateService;

  @GetMapping("/{account}/{year}")
  public AccountTaxRateDto getAccountTaxRate(@PathVariable String account, @PathVariable Integer year) {
    return accountTaxRateService.getOrCreateAccountTaxRate(account, year);
  }

  @PutMapping("/{account}/{year}")
  public AccountTaxRateDto updateAccountTaxRate(@PathVariable String account, @PathVariable Integer year,
                                                @RequestBody TaxUpdateDto taxUpdateDto) {
    return accountTaxRateService.updateAccountTaxRate(account, year, taxUpdateDto);
  }


}

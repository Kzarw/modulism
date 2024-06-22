package ru.tbank.javaconf.modulism.tax.mapper;

import org.springframework.stereotype.Component;
import ru.tbank.javaconf.modulism.tax.dto.AccountTaxRateDto;
import ru.tbank.javaconf.modulism.tax.entity.AccountTaxRate;

@Component
public class AccountTaxRateMapper {

  public AccountTaxRateDto toDto(AccountTaxRate accountTaxRate) {
    return new AccountTaxRateDto(
      accountTaxRate.getAccount(),
      accountTaxRate.getYear(),
      accountTaxRate.getTaxRate()
    );
  }

  public AccountTaxRate toEntity(AccountTaxRateDto accountTaxRateDto) {
    AccountTaxRate accountTaxRate = new AccountTaxRate();
    accountTaxRate.setAccount(accountTaxRateDto.account());
    accountTaxRate.setYear(accountTaxRateDto.year());
    accountTaxRate.setTaxRate(accountTaxRateDto.rate());
    return accountTaxRate;
  }
}
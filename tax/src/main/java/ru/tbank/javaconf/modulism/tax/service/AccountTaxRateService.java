package ru.tbank.javaconf.modulism.tax.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tbank.javaconf.modulism.tax.dto.AccountTaxRateDto;
import ru.tbank.javaconf.modulism.tax.dto.TaxUpdateDto;
import ru.tbank.javaconf.modulism.tax.entity.AccountTaxRate;
import ru.tbank.javaconf.modulism.tax.entity.TaxRateDefault;
import ru.tbank.javaconf.modulism.tax.mapper.AccountTaxRateMapper;
import ru.tbank.javaconf.modulism.tax.repository.AccountTaxRateRepository;
import ru.tbank.javaconf.modulism.tax.repository.TaxRateDefaultRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountTaxRateService {

  private final AccountTaxRateRepository accountTaxRateRepository;
  private final TaxRateDefaultRepository taxRateDefaultRepository;
  private final AccountTaxRateMapper accountTaxRateMapper;

  @Transactional
  public AccountTaxRateDto getOrCreateAccountTaxRate(String account, Integer year) {
    Optional<AccountTaxRate> optionalAccountTax = accountTaxRateRepository.findByAccountAndYear(account, year);

    return optionalAccountTax
      .map(accountTaxRateMapper::toDto)
      .orElseGet(() -> createTaxFromDefaultAndGet(account, year));
  }

  private AccountTaxRateDto createTaxFromDefaultAndGet(String account, Integer year) {
    TaxRateDefault taxRateDefault = taxRateDefaultRepository.findById(year)
      .orElseThrow(() -> new IllegalArgumentException("No tax default found for year " + year));

    AccountTaxRate accountTaxRate = new AccountTaxRate();
    accountTaxRate.setAccount(account);
    accountTaxRate.setYear(year);
    accountTaxRate.setTaxRate(taxRateDefault.getRate());

    accountTaxRate = accountTaxRateRepository.save(accountTaxRate);
    return accountTaxRateMapper.toDto(accountTaxRate);
  }

  public AccountTaxRateDto updateAccountTaxRate(String account, Integer year, TaxUpdateDto taxUpdateDto) {
    var taxRate = taxUpdateDto.taxRate();

    if (taxRate == null) {
      throw new IllegalArgumentException("Tax rate must be non-null");
    }
    if (taxRate.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Tax rate must be non-negative");
    }

    AccountTaxRate accountTaxRate = accountTaxRateRepository.findByAccountAndYear(account, year)
      .orElseGet(() -> {
        var tax = new AccountTaxRate();
        tax.setYear(year);
        tax.setAccount(account);
        return tax;
      });

    accountTaxRate.setTaxRate(taxRate);
    return accountTaxRateMapper.toDto(accountTaxRateRepository.save(accountTaxRate));
  }
}
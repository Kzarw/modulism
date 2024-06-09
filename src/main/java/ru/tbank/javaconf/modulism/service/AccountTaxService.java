package ru.tbank.javaconf.modulism.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tbank.javaconf.modulism.dto.CalculatedTaxDto;
import ru.tbank.javaconf.modulism.dto.OperationDto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountTaxService {

  private final OperationService operationService;
  private final AccountTaxRateService accountTaxRateService;

  public CalculatedTaxDto calculateTax(String account, Integer year) {
    List<OperationDto> operations = operationService.getIncomingOperations(account, year);
    Instant calculatedAt = Instant.now();

    if (operations.isEmpty()) {
      return new CalculatedTaxDto(calculatedAt, BigDecimal.ZERO, year, account);
    }

    var totalIncomes = operations.stream().map(OperationDto::amount).reduce(BigDecimal.ZERO, BigDecimal::add);
    log.info("Total incomes: {}", totalIncomes);

    var taxRate = accountTaxRateService.getOrCreateAccountTaxRate(account, year).rate();
    log.info("Tax rate: {}", taxRate);

    return new CalculatedTaxDto(
      calculatedAt,
      totalIncomes.multiply(taxRate).scaleByPowerOfTen(-2)
        .setScale(2, RoundingMode.HALF_UP),
      year,
      account
    );
  }
}

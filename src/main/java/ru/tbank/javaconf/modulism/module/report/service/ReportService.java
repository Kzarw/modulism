package ru.tbank.javaconf.modulism.module.report.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tbank.javaconf.modulism.config.properties.TaxesConfigurationProperties;
import ru.tbank.javaconf.modulism.module.operations.dto.OperationDto;
import ru.tbank.javaconf.modulism.module.tax.service.AccountTaxRateService;
import ru.tbank.javaconf.modulism.module.tax.service.AccountTaxService;
import ru.tbank.javaconf.modulism.module.operations.service.OperationService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
public class ReportService {

  private final OperationService operationService;
  private final AccountTaxRateService accountTaxRateService;

  private final AccountTaxService accountTaxService;

  private final TaxesConfigurationProperties taxesConfigurationProperties;
  private final String bankName;


  public ReportService(
    OperationService operationService,
    AccountTaxRateService accountTaxRateService,
    AccountTaxService accountTaxService,
    TaxesConfigurationProperties taxesConfigurationProperties,
    @Value("${our.awesome.bank.name}") String bankName
  ) {
    this.operationService = operationService;
    this.accountTaxRateService = accountTaxRateService;
    this.accountTaxService = accountTaxService;
    this.taxesConfigurationProperties = taxesConfigurationProperties;
    this.bankName = bankName;
  }

  public String generateReport(String accountNumber, Integer year) {
    List<OperationDto> operations = operationService.getAccountOperations(accountNumber, year);

    var incomes = operations.stream()
      .filter(o -> accountNumber.equals(o.payeeAccount()) && bankName.equals(o.payeeBank()))
      .toList();

    BigDecimal totalIncomes = incomes.stream()
      .map(OperationDto::amount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    var outcomes = operations.stream()
      .filter(o -> accountNumber.equals(o.payerAccount()) && bankName.equals(o.payerBank()))
      .toList();

    BigDecimal totalOutcomes = outcomes.stream()
      .map(OperationDto::amount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    var totalTaxesToPay = accountTaxService.calculateTax(accountNumber, year);

    BigDecimal totalPaidTaxes = outcomes.stream()
      .filter(
        o -> taxesConfigurationProperties.getBank().equals(o.payeeBank())
          && taxesConfigurationProperties.getAccount().equals(o.payeeAccount())
      )
      .map(OperationDto::amount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    StringBuilder report = new StringBuilder();
    report.append("Incoming Operations:\n");
    incomes.forEach(op -> appendOperation(report, op).append("\n"));
    report.append("Total Incomes: ")
      .append(totalIncomes.setScale(2, RoundingMode.HALF_UP))
      .append("\n")
      .append("Outgoing Operations:\n");

    outcomes.forEach(op -> appendOperation(report, op).append("\n"));
    report.append("Total Outcomes: ")
      .append(totalOutcomes.setScale(2, RoundingMode.HALF_UP))
      .append("\n")
      .append("Total Taxes to Pay: ")
      .append(totalTaxesToPay.amountToPay().setScale(2, RoundingMode.HALF_UP))
      .append("\n")
      .append("Total Paid Taxes: ")
      .append(totalPaidTaxes.setScale(2, RoundingMode.HALF_UP))
      .append("\n");

    return report.toString();
  }

  private static StringBuilder appendOperation(StringBuilder report, OperationDto operationDto) {
    return report.append(operationDto.date())
      .append("\t")
      .append(operationDto.payerAccount())
      .append("\t")
      .append(operationDto.payerBank())
      .append("\t")
      .append(operationDto.payeeAccount())
      .append("\t")
      .append(operationDto.payeeBank())
      .append("\t")
      .append(operationDto.amount());
  }

}

package ru.tbank.javaconf.modulism.report.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tbank.javaconf.modulism.operations.api.model.OperationDto;
import ru.tbank.javaconf.modulism.operations.api.service.OperationService;
import ru.tbank.javaconf.modulism.tax.api.model.CalculatedTaxDto;
import ru.tbank.javaconf.modulism.tax.api.model.TaxesConfiguration;
import ru.tbank.javaconf.modulism.tax.api.service.AccountTaxService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
public class ReportService {

  private final OperationService operationService;

  private final AccountTaxService accountTaxServiceImpl;

  private final TaxesConfiguration taxesConfiguration;
  private final String bankName;


  public ReportService(
    OperationService operationService,
    AccountTaxService accountTaxServiceImpl,
    TaxesConfiguration taxesConfigurationProperties,
    @Value("${our.awesome.bank.name}") String bankName
  ) {
    this.operationService = operationService;
    this.accountTaxServiceImpl = accountTaxServiceImpl;
    this.taxesConfiguration = taxesConfigurationProperties;
    this.bankName = bankName;
  }

  public String generateReport(CalculatedTaxDto calculatedTaxDto) {
    String accountNumber = calculatedTaxDto.account();
    var operations = operationService.getAccountOperations(accountNumber, calculatedTaxDto.year());

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

    BigDecimal totalPaidTaxes = outcomes.stream()
      .filter(
        o -> taxesConfiguration.getBank().equals(o.payeeBank())
          && taxesConfiguration.getAccount().equals(o.payeeAccount())
      )
      .map(OperationDto::amount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    return getReport(incomes, totalIncomes, outcomes, totalOutcomes, calculatedTaxDto, totalPaidTaxes);
  }

  public String generateReport(String accountNumber, Integer year) {
    List<? extends OperationDto> operations = operationService.getAccountOperations(accountNumber, year);

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

    var totalTaxesToPay = accountTaxServiceImpl.calculateTax(accountNumber, year);

    BigDecimal totalPaidTaxes = outcomes.stream()
      .filter(
        o -> taxesConfiguration.getBank().equals(o.payeeBank())
          && taxesConfiguration.getAccount().equals(o.payeeAccount())
      )
      .map(OperationDto::amount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);

    return getReport(incomes, totalIncomes, outcomes, totalOutcomes, totalTaxesToPay, totalPaidTaxes);
  }

  private static String getReport(
    List<? extends OperationDto> incomes,
    BigDecimal totalIncomes,
    List<? extends OperationDto> outcomes,
    BigDecimal totalOutcomes,
    CalculatedTaxDto totalTaxesToPay,
    BigDecimal totalPaidTaxes
  ) {
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

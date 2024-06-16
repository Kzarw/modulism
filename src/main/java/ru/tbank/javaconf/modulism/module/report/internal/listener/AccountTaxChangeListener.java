package ru.tbank.javaconf.modulism.module.report.internal.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import ru.tbank.javaconf.modulism.module.report.service.ReportService;
import ru.tbank.javaconf.modulism.module.tax.api.model.CalculatedTaxDto;

@Component
@RequiredArgsConstructor
public class AccountTaxChangeListener {

  private final ReportService reportService;
  private final KafkaTemplate<String, String> kafkaTemplate;


  @ApplicationModuleListener
  void onTaxChange(CalculatedTaxDto calculatedTaxDto) {
    var report = reportService.generateReport(calculatedTaxDto);
    kafkaTemplate.send("reports.tax", calculatedTaxDto.account(), report);
  }
}

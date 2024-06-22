package ru.tbank.javaconf.modulism.report.internal.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import ru.tbank.javaconf.modulism.report.service.ReportService;
import ru.tbank.javaconf.modulism.tax.api.model.CalculatedTaxDto;

@Controller
@RequiredArgsConstructor
public class AccountTaxChangeListener {

  private final ReportService reportService;
  private final KafkaTemplate<String, String> kafkaTemplate;


  //@ApplicationModuleListener
  @EventListener
  void onTaxChange(CalculatedTaxDto calculatedTaxDto) {
    var report = reportService.generateReport(calculatedTaxDto);
    kafkaTemplate.send("reports.tax", calculatedTaxDto.account(), report);
  }
}

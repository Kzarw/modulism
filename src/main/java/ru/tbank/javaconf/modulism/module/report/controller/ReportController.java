package ru.tbank.javaconf.modulism.module.report.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.javaconf.modulism.module.report.service.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

  private final ReportService reportService;

  @GetMapping(path = "/{account}/{year}", produces = MediaType.TEXT_PLAIN_VALUE)
  public String getReport(@PathVariable String account, @PathVariable Integer year) {
    return reportService.generateReport(account, year);
  }
}
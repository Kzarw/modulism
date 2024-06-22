package ru.tbank.javaconf.modulism.report;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@ConditionalOnProperty(value = "application.modules.report.enabled", havingValue = "true", matchIfMissing = true)
public class ReportAutoConfiguration {
}

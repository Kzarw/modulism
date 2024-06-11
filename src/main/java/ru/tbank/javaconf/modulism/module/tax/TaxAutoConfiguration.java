package ru.tbank.javaconf.modulism.module.tax;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@ConditionalOnProperty(value = "application.modules.tax.enabled", havingValue = "true", matchIfMissing = true)
public class TaxAutoConfiguration {
}

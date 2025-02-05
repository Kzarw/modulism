package ru.tbank.javaconf.modulism.tax;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@EnableConfigurationProperties(TaxesConfigurationProperties.class)
@ConditionalOnProperty(value = "application.modules.tax.enabled", havingValue = "true", matchIfMissing = true)
public class TaxAutoConfiguration {
}

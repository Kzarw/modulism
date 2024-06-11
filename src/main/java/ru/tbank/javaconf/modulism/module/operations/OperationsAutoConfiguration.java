package ru.tbank.javaconf.modulism.module.operations;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

@AutoConfiguration
@ComponentScan
@ConditionalOnProperty(value = "application.modules.operations.enabled", havingValue = "true", matchIfMissing = true)
public class OperationsAutoConfiguration {
}

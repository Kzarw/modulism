package ru.tbank.javaconf.modulism.operations;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@AutoConfiguration
@ComponentScan
@ConditionalOnProperty(value = "application.modules.operations.enabled", havingValue = "true", matchIfMissing = true)
@EnableJdbcRepositories
@EnableTransactionManagement
public class OperationsAutoConfiguration {
}

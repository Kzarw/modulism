package ru.tbank.javaconf.modulism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.modulith.Modulithic;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.tbank.javaconf.modulism.config.properties.TaxesConfigurationProperties;

@Modulithic
@EnableConfigurationProperties(TaxesConfigurationProperties.class)
@SpringBootApplication(scanBasePackages = "ru.tbank.javaconf.modulism.config")
@EnableAsync // required for ApplicationModuleEvents to work
@EnableTransactionManagement
@EnableScheduling
public class ModulismApplication {

  public static void main(String[] args) {
    SpringApplication.run(ModulismApplication.class, args);
  }

}

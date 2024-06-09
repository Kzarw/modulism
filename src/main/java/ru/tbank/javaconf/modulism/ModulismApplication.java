package ru.tbank.javaconf.modulism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.tbank.javaconf.modulism.config.properties.TaxesConfigurationProperties;

@EnableConfigurationProperties(TaxesConfigurationProperties.class)
@SpringBootApplication
public class ModulismApplication {

  public static void main(String[] args) {
    SpringApplication.run(ModulismApplication.class, args);
  }

}

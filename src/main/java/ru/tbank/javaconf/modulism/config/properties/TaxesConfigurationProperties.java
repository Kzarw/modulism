package ru.tbank.javaconf.modulism.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@Getter
@ConfigurationProperties("taxes")
public class TaxesConfigurationProperties {

  private final String bank;
  private final String account;

  @ConstructorBinding
  public TaxesConfigurationProperties(String bank, String account) {
    this.bank = bank;
    this.account = account;
  }
}

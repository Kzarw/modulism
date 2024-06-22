package ru.tbank.javaconf.modulism.tax;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import ru.tbank.javaconf.modulism.tax.api.model.TaxesConfiguration;


@Getter
@ConfigurationProperties("taxes")
public class TaxesConfigurationProperties implements TaxesConfiguration  {

  private final String bank;
  private final String account;

  @ConstructorBinding
  public TaxesConfigurationProperties(String bank, String account) {
    this.bank = bank;
    this.account = account;
  }
}

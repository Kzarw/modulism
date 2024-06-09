package ru.tbank.javaconf.modulism.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@Table("account_tax_rates")
public class AccountTaxRate {

  @Id
  private Long id;

  @NotNull
  private String account;

  @NotNull
  private Integer year;

  @NotNull
  @Min(0)
  @Max(100)
  private BigDecimal taxRate;
}
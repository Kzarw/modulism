package ru.tbank.javaconf.modulism.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@Table("tax_rate_defaults")
public class TaxRateDefault {

  @Id
  private Integer year;

  @NotNull
  @Min(0)
  private BigDecimal rate;
}
package ru.tbank.javaconf.modulism.operations.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Table("operations")
public class Operation {
  @Id
  private Long id;

  @NotNull
  @Size(max = 255, min = 1)
  private String payerBank;

  @NotNull
  @Size(max = 255, min = 1)
  private String payeeBank;

  @NotNull
  @Pattern(regexp = "\\d{6}")
  private String payerAccount;

  @NotNull
  @Pattern(regexp = "\\d{6}")
  private String payeeAccount;

  @NotNull
  @Min(0)
  private BigDecimal amount;

  @Null
  @Pattern(regexp = "\\d{6}")
  private String transactionNumber;

  @NotNull
  private Instant datetime;
}

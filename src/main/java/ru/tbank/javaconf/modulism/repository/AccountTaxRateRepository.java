package ru.tbank.javaconf.modulism.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.entity.AccountTaxRate;

import java.util.Optional;

public interface AccountTaxRateRepository extends CrudRepository<AccountTaxRate, Long> {
  Optional<AccountTaxRate> findByAccountAndYear(String account, Integer year);
}
package ru.tbank.javaconf.modulism.module.tax.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.module.tax.entity.TaxRateDefault;

public interface TaxRateDefaultRepository extends CrudRepository<TaxRateDefault, Integer> {
}
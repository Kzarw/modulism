package ru.tbank.javaconf.modulism.tax.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.tax.entity.TaxRateDefault;

public interface TaxRateDefaultRepository extends CrudRepository<TaxRateDefault, Integer> {
}
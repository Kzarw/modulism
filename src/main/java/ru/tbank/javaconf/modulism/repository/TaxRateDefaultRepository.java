package ru.tbank.javaconf.modulism.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.entity.TaxRateDefault;

public interface TaxRateDefaultRepository extends CrudRepository<TaxRateDefault, Integer> {
}
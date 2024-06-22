package ru.tbank.javaconf.modulism.tax.internal.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import ru.tbank.javaconf.modulism.operations.api.model.CreatedOperationEvent;
import ru.tbank.javaconf.modulism.tax.api.service.AccountTaxService;

@Component
@RequiredArgsConstructor
public class OperationEventListener {

  private final AccountTaxService accountTaxService;
  private final ApplicationEventPublisher eventPublisher;

  @ApplicationModuleListener
  void onAccountOperationsChange(CreatedOperationEvent operationEvent) {
    var calculatedTax = accountTaxService.calculateTax(operationEvent.account(), operationEvent.date().getYear());
    eventPublisher.publishEvent(calculatedTax);
  }
}


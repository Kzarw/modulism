package ru.tbank.javaconf.modulism.operations.api.service;

import ru.tbank.javaconf.modulism.operations.api.model.OperationDto;

import java.util.List;

public interface OperationService {
  Iterable<? extends OperationDto> getAccountOperations(String account);

  List<? extends OperationDto> getIncomingOperations(String account, Integer year);

  List<? extends OperationDto> getAccountOperations(String account, Integer year);
}
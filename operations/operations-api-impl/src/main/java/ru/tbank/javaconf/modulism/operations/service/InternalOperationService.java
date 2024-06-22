package ru.tbank.javaconf.modulism.operations.service;


import ru.tbank.javaconf.modulism.operations.dto.OperationDto;
import ru.tbank.javaconf.modulism.operations.api.service.OperationService;
import ru.tbank.javaconf.modulism.operations.dto.event.CreateOperationDto;

import java.util.List;
import java.util.Optional;

public interface InternalOperationService extends OperationService {
  @Override
  Iterable<OperationDto> getAccountOperations(String account);

  Optional<OperationDto> getOperationById(Long id);

  OperationDto createOperation(CreateOperationDto operationDto);

  void deleteOperation(String transactionNumber);

  @Override
  List<OperationDto> getIncomingOperations(String account, Integer year);

  @Override
  List<OperationDto> getAccountOperations(String account, Integer year);
}

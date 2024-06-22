package ru.tbank.javaconf.modulism.operations.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import ru.tbank.javaconf.modulism.operations.api.model.CreatedOperationEvent;
import ru.tbank.javaconf.modulism.operations.dto.OperationDto;
import ru.tbank.javaconf.modulism.operations.dto.event.CreateOperationDto;
import ru.tbank.javaconf.modulism.operations.entity.Operation;
import ru.tbank.javaconf.modulism.operations.mapper.OperationMapper;
import ru.tbank.javaconf.modulism.operations.repository.OperationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Controller
@Slf4j
public class OperationServiceImpl implements InternalOperationService {
  private final OperationRepository operationRepository;
  private final OperationMapper operationMapper;
  private final ApplicationEventPublisher eventPublisher;

  private final String bankName;

  @Autowired
  public OperationServiceImpl(
    OperationRepository operationRepository,
    OperationMapper operationMapper,
    ApplicationEventPublisher eventPublisher,
    @Value("${our.awesome.bank.name}") String bankName
  ) {
    this.operationRepository = operationRepository;
    this.operationMapper = operationMapper;
    this.eventPublisher = eventPublisher;
    this.bankName = bankName;
  }


  @Override
  public Iterable<OperationDto> getAccountOperations(String account) {
    log.info("Requested operations for account {}", account);
    return StreamSupport.stream(operationRepository.findByAccountAndBank(account, bankName).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }

  @Override
  public Optional<OperationDto> getOperationById(Long id) {
    return operationRepository.findById(id).map(operationMapper::toDto);
  }

  @Override
  public OperationDto createOperation(CreateOperationDto operationDto) {
    Operation operation = operationMapper.toEntity(operationDto);
    Operation savedOperation = operationRepository.save(operation);
    var dto = operationMapper.toDto(savedOperation);
    if (bankName.equals(operationDto.payeeBank())) {
      eventPublisher.publishEvent(new CreatedOperationEvent(operationDto.payeeAccount(), dto.date()));
    }
    if (bankName.equals(operationDto.payerBank())) {
      eventPublisher.publishEvent(new CreatedOperationEvent(operationDto.payerAccount(), dto.date()));
    }

    return dto;
  }

  @Override
  public void deleteOperation(String transactionNumber) {
    operationRepository.deleteByTransactionNumber(transactionNumber);
  }

  @Override
  public List<OperationDto> getIncomingOperations(String account, Integer year) {
    log.info("Requested incoming operations for account {} and year {}", account, year);
    return StreamSupport.stream(operationRepository.findIncomingByAccountAndBankAndYear(account, bankName, year).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }

  @Override
  public List<OperationDto> getAccountOperations(String account, Integer year) {
    log.info("Requested operations for account {} and year {}", account, year);
    return StreamSupport.stream(operationRepository.findByAccountAndBank(account, bankName, year).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }
}

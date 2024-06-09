package ru.tbank.javaconf.modulism.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.tbank.javaconf.modulism.dto.OperationDto;
import ru.tbank.javaconf.modulism.dto.event.CreateOperationDto;
import ru.tbank.javaconf.modulism.entity.Operation;
import ru.tbank.javaconf.modulism.mapper.OperationMapper;
import ru.tbank.javaconf.modulism.repository.OperationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class OperationService {
  private final OperationRepository operationRepository;
  private final OperationMapper operationMapper;

  private final String bankName;

  @Autowired
  public OperationService(
    OperationRepository operationRepository,
    OperationMapper operationMapper,
    @Value("${our.awesome.bank.name}") String bankName
  ) {
    this.operationRepository = operationRepository;
    this.operationMapper = operationMapper;
    this.bankName = bankName;
  }


  public Iterable<OperationDto> getAccountOperations(String account) {
    return StreamSupport.stream(operationRepository.findByAccountAndBank(account, bankName).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }

  public Optional<OperationDto> getOperationById(Long id) {
    return operationRepository.findById(id).map(operationMapper::toDto);
  }

  public OperationDto createOperation(CreateOperationDto operationDto) {
    Operation operation = operationMapper.toEntity(operationDto);
    Operation savedOperation = operationRepository.save(operation);
    return operationMapper.toDto(savedOperation);
  }

  public void deleteOperation(String trasactionNumber) {
    operationRepository.deleteByTransactionNumber(trasactionNumber);
  }

  public List<OperationDto> getIncomingOperations(String account, Integer year) {
    return StreamSupport.stream(operationRepository.findIncomingByAccountAndBankAndYear(account, bankName, year).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }

  public List<OperationDto> getAccountOperations(String account, Integer year) {
    return StreamSupport.stream(operationRepository.findByAccountAndBank(account, bankName, year).spliterator(), false)
      .map(operationMapper::toDto)
      .toList();
  }
}

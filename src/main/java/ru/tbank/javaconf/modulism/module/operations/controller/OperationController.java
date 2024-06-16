package ru.tbank.javaconf.modulism.module.operations.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tbank.javaconf.modulism.module.operations.dto.OperationDto;
import ru.tbank.javaconf.modulism.module.operations.service.OperationServiceImpl;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

  private final OperationServiceImpl operationService;
//  private final EventsPublicationsController eventsPublicationsController;

  @GetMapping("/{account}")
  public Iterable<OperationDto> getAllOperations(@PathVariable String account) {
    return operationService.getAccountOperations(account);
  }

  @GetMapping("/{account}/{id}")
  public ResponseEntity<OperationDto> getOperationById(@PathVariable String account, @PathVariable Long id) {
    Optional<OperationDto> operationDto = operationService.getOperationById(id)
      .filter(o -> Objects.equals(o.payeeAccount(), account) || Objects.equals(o.payerAccount(), account));
    return operationDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
}
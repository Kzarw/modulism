package ru.tbank.javaconf.modulism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tbank.javaconf.modulism.dto.OperationDto;
import ru.tbank.javaconf.modulism.service.OperationService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {

  private final OperationService operationService;

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
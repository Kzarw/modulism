package ru.tbank.javaconf.modulism.module.operations.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.tbank.javaconf.modulism.module.operations.entity.Operation;

public interface OperationRepository extends CrudRepository<Operation, Long> {
  void deleteByTransactionNumber(String transactionNumber);

  @Query(
    //language=SQL
    """
      SELECT * 
      FROM operations
      WHERE (payer_account = :account and payer_bank = :bankName) or (payee_account = :account and payee_bank = :bankName)
      """
  )
  Iterable<Operation> findByAccountAndBank(String account, String bankName);

  @Query(
    //language=SQL
    """
      SELECT * 
      FROM operations
      WHERE (payee_account = :account and payee_bank = :bankName)
        AND year(datetime) = :year 
      """
  )
  Iterable<Operation> findIncomingByAccountAndBankAndYear(String account, String bankName, Integer year);
  @Query(
    //language=SQL
    """
      SELECT * 
      FROM operations
      WHERE ((payer_account = :account and payer_bank = :bankName) or (payee_account = :account and payee_bank = :bankName))
        AND year(datetime) = :year 
      """
  )
  Iterable<Operation> findByAccountAndBank(String account, String bankName, Integer year);
}

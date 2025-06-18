package com.gate.demo.repository;

import com.gate.demo.model.Transaction;
import com.gate.demo.model.TransactionType;
import jakarta.persistence.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findByAccountId(UUID accountId, Pageable pageable);

    @Query("""
    SELECT COUNT(t) as totalTransactions, COALESCE(SUM(t.amount), 0) as totalAmount
    FROM Transaction t
    WHERE (:type IS NULL OR t.type = :type)
    AND t.timestamp >= :startDate AND t.timestamp <= :endDate
    """)
    Tuple getTransactionReport(@Param("type") TransactionType type,
                               @Param("startDate") LocalDateTime startDate,
                               @Param("endDate") LocalDateTime endDate);


}

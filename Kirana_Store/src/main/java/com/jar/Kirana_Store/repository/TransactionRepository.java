package com.jar.Kirana_Store.repository;

// TransactionRepository.java
import org.springframework.data.jpa.repository.JpaRepository;

import com.jar.Kirana_Store.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;
 
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}

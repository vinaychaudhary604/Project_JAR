package com.jar.Kirana_Store.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;  
import com.jar.Kirana_Store.model.Transaction; 
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    
    Optional<Transaction> findById(Long transactionId); 
}
 
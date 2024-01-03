// package com.jar.Kirana_Store.service;

// // TransactionServiceImpl.java
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.jar.Kirana_Store.model.Transaction;
// import com.jar.Kirana_Store.repository.TransactionRepository;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.time.LocalTime;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;

// @Service
// public class TransactionServiceImpl implements TransactionService {

//     @Autowired
//     private TransactionRepository transactionRepository;

//     @Override
//     public Transaction recordTransaction(Transaction transaction) {
//         transaction.setTimestamp(LocalDateTime.now());
//         return transactionRepository.save(transaction);
//     }

//     @Override
//     public List<Transaction> getDailyTransactions() {
//         LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
//         LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
//         return transactionRepository.findByTimestampBetween(startOfDay, endOfDay);
//     }

//     @Override
//     public Map<String, BigDecimal> getDailyTransactionSummaries() {
//         List<Transaction> dailyTransactions = getDailyTransactions();

//         return dailyTransactions.stream()
//                 .collect(Collectors.groupingBy(Transaction::getType,
//                         Collectors.reducing(BigDecimal.ZERO, Transaction::getAmount, BigDecimal::add)));
//     }
// }

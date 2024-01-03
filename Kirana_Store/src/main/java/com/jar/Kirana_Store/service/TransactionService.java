package com.jar.Kirana_Store.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jar.Kirana_Store.model.Transaction;
import com.jar.Kirana_Store.repository.TransactionRepository;
import com.jar.Kirana_Store.transaction_request_dto.TransactionRequest; 

@Component
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    public Transaction recordTransaction(TransactionRequest transactionRequest) {
        // Fetch the actual value of the provided currency using the external API
        // BigDecimal actualCurrencyValueUSD = currencyConversionService.fetchActualCurrencyValue(transactionRequest.getCurrency(), "USD");
        // BigDecimal actualCurrencyValueINR = currencyConversionService.fetchActualCurrencyValue(transactionRequest.getCurrency(), "INR");

        // Convert the provided amount to INR
        BigDecimal amountInINR = currencyConversionService.convertToINR(transactionRequest.getAmount(), transactionRequest.getCurrency());
        BigDecimal amountInUSD = currencyConversionService.convertToUSD(transactionRequest.getAmount(), transactionRequest.getCurrency());

        Transaction transaction = new Transaction();
        transaction.setAmountINR(amountInINR);
        transaction.setCurrencyINR("INR"); // Indicate the currency as INR

        transaction.setAmountUSD(amountInUSD);
        transaction.setCurrencyUSD("USD");

        transaction.setTimestamp(LocalDateTime.now());
        transaction.setType(transactionRequest.getType());

        return transactionRepository.save(transaction);
    }
    
    

    // public List<Transaction> getTransactionsByDateRange(LocalDateTime start, LocalDateTime end) {
    //     return transactionRepository.findByTimestampBetween(start, end);
    // }

    // public Map<LocalDate, List<Transaction>> getDailyReport(LocalDate startDate, LocalDate endDate) {
    //     List<Transaction> allTransactions = transactionRepository.findByTimestampBetween(
    //             startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX)
    //     );

    //     return allTransactions.stream()
    //             .collect(Collectors.groupingBy(transaction -> transaction.getTimestamp().toLocalDate()));
    // }

    public Map<LocalDate, List<Transaction>> getDailyReport(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        List<Transaction> allTransactions = transactionRepository.findByTimestampBetween(startDateTime, endDateTime);

        return allTransactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getTimestamp().toLocalDate()));
    } 
}

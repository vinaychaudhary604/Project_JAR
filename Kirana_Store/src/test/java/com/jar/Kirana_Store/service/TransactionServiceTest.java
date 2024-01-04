package com.jar.Kirana_Store.service;

import com.jar.Kirana_Store.exception.NotFoundException;
import com.jar.Kirana_Store.model.Transaction;
import com.jar.Kirana_Store.model.TransactionType;
import com.jar.Kirana_Store.repository.TransactionRepository;
import com.jar.Kirana_Store.transaction_request_dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @Test
    void testRecordTransaction() {
        
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(new BigDecimal("100.00"));
        transactionRequest.setCurrency("USD");
        transactionRequest.setType(TransactionType.DEBIT);
 
        when(currencyConversionService.convertToINR(any(BigDecimal.class), any(String.class)))
                .thenReturn(new BigDecimal("7500.00")); // Assuming conversion rate is 75 for 1 USD
        when(currencyConversionService.convertToUSD(any(BigDecimal.class), any(String.class)))
                .thenReturn(new BigDecimal("1.00")); 

        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction recordedTransaction = transactionService.recordTransaction(transactionRequest);

        verify(transactionRepository, times(1)).save(any(Transaction.class));

        assertNotNull(recordedTransaction);
        assertEquals(new BigDecimal("7500.00"), recordedTransaction.getAmountINR());
        assertEquals("INR", recordedTransaction.getCurrencyINR());
        assertEquals(new BigDecimal("1.00"), recordedTransaction.getAmountUSD());
        assertEquals("USD", recordedTransaction.getCurrencyUSD());
        assertEquals(TransactionType.DEBIT, recordedTransaction.getType());
    }

    @Test
    void testUpdateTransactionById() {
        
        Long transactionId = 1L;
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setAmountINR(new BigDecimal("7500.00"));
        updatedTransaction.setCurrencyINR("INR");
        updatedTransaction.setAmountUSD(new BigDecimal("1.00"));
        updatedTransaction.setCurrencyUSD("USD");
        updatedTransaction.setType(TransactionType.DEBIT);
 
        Transaction existingTransaction = new Transaction();
        existingTransaction.setAmountINR(new BigDecimal("5000.00"));
        existingTransaction.setCurrencyINR("INR");
        existingTransaction.setAmountUSD(new BigDecimal("0.67"));
        existingTransaction.setCurrencyUSD("USD");
        existingTransaction.setType(TransactionType.CREDIT);

        when(transactionRepository.findById(eq(transactionId))).thenReturn(Optional.of(existingTransaction));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
 
        Transaction updatedTransactionResult = transactionService.updateTransactionById(transactionId, updatedTransaction);

        
        verify(transactionRepository, times(1)).findById(eq(transactionId));
        verify(transactionRepository, times(1)).save(any(Transaction.class));

        
        assertNotNull(updatedTransactionResult);
        assertEquals(new BigDecimal("7500.00"), updatedTransactionResult.getAmountINR());
        assertEquals("INR", updatedTransactionResult.getCurrencyINR());
        assertEquals(new BigDecimal("1.00"), updatedTransactionResult.getAmountUSD());
        assertEquals("USD", updatedTransactionResult.getCurrencyUSD());
        assertEquals(TransactionType.DEBIT, updatedTransactionResult.getType());
    }

    @Test
    void testUpdateTransactionByIdNotFound() {
        
        Long transactionId = 1L;
        Transaction updatedTransaction = new Transaction();

        
        when(transactionRepository.findById(eq(transactionId))).thenReturn(Optional.empty());

        
        assertThrows(NotFoundException.class, () ->
                transactionService.updateTransactionById(transactionId, updatedTransaction));

                
        verify(transactionRepository, times(1)).findById(eq(transactionId));

        verify(transactionRepository, never()).save(any(Transaction.class));
    }

    @Test
    void testGetDailyReport() {
        
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 5);

        
        List<Transaction> transactions = Collections.singletonList(new Transaction());
        transactions.get(0).setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0)); // Set a non-null timestamp

        when(transactionRepository.findByTimestampBetween(
                eq(LocalDateTime.of(startDate, LocalTime.MIN)),
                eq(LocalDateTime.of(endDate, LocalTime.MAX))))
                .thenReturn(transactions);

                
        Map<LocalDate, List<Transaction>> dailyReport = transactionService.getDailyReport(startDate, endDate);

        verify(transactionRepository, times(1)).findByTimestampBetween(
                eq(LocalDateTime.of(startDate, LocalTime.MIN)),
                eq(LocalDateTime.of(endDate, LocalTime.MAX)));

        assertNotNull(dailyReport);
        assertFalse(dailyReport.isEmpty());
        assertTrue(dailyReport.containsKey(startDate));
 
        List<Transaction> dailyTransactions = dailyReport.get(startDate);
        assertNotNull(dailyTransactions);
 
        for (Transaction transaction : dailyTransactions) {
            assertNotNull(transaction.getTimestamp());
        }

        assertEquals(transactions, dailyTransactions);
    }

}

package com.jar.Kirana_Store.controllerTest;

import com.jar.Kirana_Store.controller.TransactionController;
import com.jar.Kirana_Store.model.Transaction;
import com.jar.Kirana_Store.service.TransactionService;
import com.jar.Kirana_Store.transaction_request_dto.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Test
    void testRecordTransaction() {
        
        TransactionRequest transactionRequest = new TransactionRequest(/* provide necessary data */);

        when(transactionService.recordTransaction(eq(transactionRequest))).thenReturn(new Transaction());

        ResponseEntity<Transaction> responseEntity = transactionController.recordTransaction(transactionRequest);

        verify(transactionService, times(1)).recordTransaction(eq(transactionRequest));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetDailyReport() {
        
        when(transactionService.getDailyReport(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.singletonMap(LocalDate.now(), Collections.singletonList(new Transaction())));
                
        ResponseEntity<Map<LocalDate, List<Transaction>>> responseEntity = transactionController.getDailyReport(LocalDate.now(), LocalDate.now());

        verify(transactionService, times(1)).getDailyReport(any(LocalDate.class), any(LocalDate.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateTransaction() {
        
        Long transactionId = 1L;
        Transaction updatedTransaction = new Transaction(/* provide necessary data */);

        when(transactionService.updateTransactionById(eq(transactionId), eq(updatedTransaction))).thenReturn(new Transaction());

        ResponseEntity<Transaction> responseEntity = transactionController.updateTransaction(transactionId, updatedTransaction);

        verify(transactionService, times(1)).updateTransactionById(eq(transactionId), eq(updatedTransaction));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}

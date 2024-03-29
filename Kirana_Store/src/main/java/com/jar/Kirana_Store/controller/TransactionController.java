package com.jar.Kirana_Store.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
  
import com.jar.Kirana_Store.model.Transaction;
import com.jar.Kirana_Store.service.TransactionService;
import com.jar.Kirana_Store.transaction_request_dto.TransactionRequest;
 

import java.time.LocalDate; 
import java.util.List;
import java.util.Map;  
 
 
@RestController 
public class TransactionController {

    @Autowired
    private TransactionService transactionService;  

    @PostMapping("/record")
    public ResponseEntity<Transaction> recordTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction recordedTransaction = transactionService.recordTransaction(transactionRequest);
        return new ResponseEntity<>(recordedTransaction, HttpStatus.CREATED);
    } 

    @GetMapping("/daily_report")
    public ResponseEntity<Map<LocalDate, List<Transaction>>> getDailyReport(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) { 

        Map<LocalDate, List<Transaction>> dailyReport = transactionService.getDailyReport(start, end);
        return new ResponseEntity<>(dailyReport, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Transaction> updateTransaction(
            @RequestParam Long transactionId,
            @RequestBody Transaction updatedTransaction) { 

        Transaction updatedTransactionResult = transactionService.updateTransactionById(transactionId, updatedTransaction);

        return new ResponseEntity<>(updatedTransactionResult, HttpStatus.OK);  
    }  

}
 
package com.jar.Kirana_Store.transaction_request_dtoTest;

import com.jar.Kirana_Store.model.TransactionType;
import com.jar.Kirana_Store.transaction_request_dto.TransactionRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionRequestTest {

    @Test
    void testGetterAndSetter() {
        
        TransactionRequest transactionRequest = new TransactionRequest();
        BigDecimal amount = new BigDecimal("100.00");
        String currency = "USD";
        TransactionType type = TransactionType.DEBIT;

        
        transactionRequest.setAmount(amount);
        transactionRequest.setCurrency(currency);
        transactionRequest.setType(type);
        

        assertEquals(amount, transactionRequest.getAmount());
        assertEquals(currency, transactionRequest.getCurrency());
        assertEquals(type, transactionRequest.getType());
    }
}

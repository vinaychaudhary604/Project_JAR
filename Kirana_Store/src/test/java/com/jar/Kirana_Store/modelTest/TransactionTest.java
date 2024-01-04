package com.jar.Kirana_Store.modelTest;

import com.jar.Kirana_Store.model.Transaction;
import com.jar.Kirana_Store.model.TransactionType;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    @Test
    void testGettersAndSetters() {
        
        Transaction transaction = new Transaction();
        Long id = 1L;
        LocalDateTime timestamp = LocalDateTime.now();
        BigDecimal amountINR = new BigDecimal("100.00");
        String currencyINR = "INR";
        BigDecimal amountUSD = new BigDecimal("1.50");
        String currencyUSD = "USD";
        TransactionType type = TransactionType.CREDIT;

        transaction.setId(id);
        transaction.setTimestamp(timestamp);
        transaction.setAmountINR(amountINR);
        transaction.setCurrencyINR(currencyINR);
        transaction.setAmountUSD(amountUSD);
        transaction.setCurrencyUSD(currencyUSD);
        transaction.setType(type);

        assertEquals(id, transaction.getId());
        assertEquals(timestamp, transaction.getTimestamp());
        assertEquals(amountINR, transaction.getAmountINR());
        assertEquals(currencyINR, transaction.getCurrencyINR());
        assertEquals(amountUSD, transaction.getAmountUSD());
        assertEquals(currencyUSD, transaction.getCurrencyUSD());
        assertEquals(type, transaction.getType());
    }
}

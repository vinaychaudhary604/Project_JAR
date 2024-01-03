package com.jar.Kirana_Store.transaction_request_dto;

import java.math.BigDecimal;

import com.jar.Kirana_Store.model.TransactionType;

import lombok.Getter;
import lombok.Setter;

// TransactionRequest.java
@Getter
@Setter
public class TransactionRequest {
    private BigDecimal amount;
    private String currency;
    private TransactionType type;

    // getters and setters
}

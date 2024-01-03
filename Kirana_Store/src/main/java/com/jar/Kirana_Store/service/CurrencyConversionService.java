package com.jar.Kirana_Store.service;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service; 
  
import java.math.BigDecimal;
 
@Service
public class CurrencyConversionService {
    @Value("${currency.api.url}")
    private String currencyApiUrl; // External API for currency conversion

    public BigDecimal convertToINR(BigDecimal amount, String sourceCurrency) {
        // Implement real-time currency conversion using the provided API
        // ...

        // For simplicity, let's assume a conversion factor is retrieved from the API
        BigDecimal conversionFactor = getConversionFactor(sourceCurrency);

        return amount.multiply(conversionFactor);
    }

    private BigDecimal getConversionFactor(String sourceCurrency) {
        // Call the external API to get the conversion factor
        // ...

        // For simplicity, return a fixed conversion factor
        return BigDecimal.valueOf(75.0);
    }
}

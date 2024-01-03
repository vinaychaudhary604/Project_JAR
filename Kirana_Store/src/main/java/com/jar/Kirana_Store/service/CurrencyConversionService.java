package com.jar.Kirana_Store.service;
  
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal; 

@Service
public class CurrencyConversionService {

    private final String apiUrl = "https://api.fxratesapi.com/latest";
    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal fetchActualCurrencyValue(String baseCurrency, String targetCurrency) {
        String url = apiUrl + "?base=" + baseCurrency + "&symbols=" + targetCurrency;
        ResponseEntity<CurrencyApiResponse> responseEntity = restTemplate.getForEntity(url, CurrencyApiResponse.class);
        CurrencyApiResponse response = responseEntity.getBody();

        if (response != null && response.getRates() != null) {
            Object rateObject = response.getRates().get(targetCurrency);

            if (rateObject instanceof Number) {
                // Convert to BigDecimal if it's a Number (including Double)
                return BigDecimal.valueOf(((Number) rateObject).doubleValue());
            } else if (rateObject instanceof Boolean) {
                // Handle Boolean value (if needed)
                return BigDecimal.ONE; // Default to 1.0
            }
        }
 
        return BigDecimal.ONE;
    }

    public BigDecimal convertToINR(BigDecimal amount, String baseCurrency) {
        // Fetch the actual currency value using the provided currency
        BigDecimal actualCurrencyValue = fetchActualCurrencyValue(baseCurrency, "INR");

        // Perform currency conversion to INR using the actual currency value
        return amount.multiply(actualCurrencyValue);
    }

    public BigDecimal convertToUSD(BigDecimal amount, String baseCurrency) {
        // Fetch the actual currency value using the provided currency
        BigDecimal actualCurrencyValue = fetchActualCurrencyValue(baseCurrency, "USD");

        // Perform currency conversion to USD using the actual currency value
        return amount.multiply(actualCurrencyValue);
    } 
}

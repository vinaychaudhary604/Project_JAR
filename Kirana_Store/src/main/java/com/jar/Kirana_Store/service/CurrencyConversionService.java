package com.jar.Kirana_Store.service;
  
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.jar.Kirana_Store.exception.CurrencyNotSupportedByApiException;

import java.math.BigDecimal; 

@Service
public class CurrencyConversionService {

    private final String apiUrl = "https://api.fxratesapi.com/latest";
    private final RestTemplate restTemplate = new RestTemplate();

    public BigDecimal fetchActualCurrencyValue(String baseCurrency, String targetCurrency) {
        try {
            String url = apiUrl + "?base=" + baseCurrency + "&symbols=" + targetCurrency;
            ResponseEntity<CurrencyApiResponse> responseEntity = restTemplate.getForEntity(url, CurrencyApiResponse.class);
            CurrencyApiResponse response = responseEntity.getBody();

            if (response != null && response.getRates() != null) {
                Object rateObject = response.getRates().get(targetCurrency);

                if (rateObject instanceof Number) { 
                    return BigDecimal.valueOf(((Number) rateObject).doubleValue());
                } else if (rateObject instanceof Boolean) { 
                    return BigDecimal.ONE; 
                }
            }
        } catch (RestClientException e) { 
            e.printStackTrace();
        } 
        throw new CurrencyNotSupportedByApiException("Currency is not supported by the API.");
    }  

    public BigDecimal convertToINR(BigDecimal amount, String baseCurrency) {
        
        BigDecimal actualCurrencyValue = fetchActualCurrencyValue(baseCurrency, "INR");
 
        return amount.multiply(actualCurrencyValue);
    }

    public BigDecimal convertToUSD(BigDecimal amount, String baseCurrency) { 

        BigDecimal actualCurrencyValue = fetchActualCurrencyValue(baseCurrency, "USD");

        return amount.multiply(actualCurrencyValue);
    } 
}

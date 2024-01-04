package com.jar.Kirana_Store.service;
 
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyApiResponseTest {

    @Test
    void testJsonAnySetter() {
        
        CurrencyApiResponse currencyApiResponse = new CurrencyApiResponse();

        currencyApiResponse.setRates("USD", 1.20);
        currencyApiResponse.setRates("EUR", 0.85);
        
        Map<String, Object> rates = currencyApiResponse.getRates();
        assertNotNull(rates);
        assertEquals(2, rates.size());
        assertTrue(rates.containsKey("USD"));
        assertTrue(rates.containsKey("EUR"));
        assertEquals(1.20, rates.get("USD"));
        assertEquals(0.85, rates.get("EUR"));
    }
}


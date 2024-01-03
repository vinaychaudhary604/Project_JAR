package com.jar.Kirana_Store.service;


import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class CurrencyApiResponse {

    private Map<String, Object> rates = new HashMap<>();

    public Map<String, Object> getRates() {
        return rates;
    }

    @JsonAnySetter
    public void setRates(String currency, Object rate) {
        rates.put(currency, rate);
    }
}


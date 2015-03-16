package com.frontier.ExchangeRates.parseParams;

import java.io.Serializable;
import java.util.List;

public class RatesList implements Serializable{

    private List<Rate> exchangeRate;

    public List<Rate> getRatesList() {
        return exchangeRate;
    }
}
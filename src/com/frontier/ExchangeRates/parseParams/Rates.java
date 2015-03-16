package com.frontier.ExchangeRates.parseParams;

import java.io.Serializable;
import java.util.List;

public class Rates implements Serializable{

    private String date;
    private List<Rate> ratesList;

    public Rates(String date, List<Rate> ratesList) {
        this.date = date;
        this.ratesList = ratesList;
    }

    public String getDate() {
        return date;
    }

    public List<Rate> getRatesList() {
        return ratesList;
    }
}

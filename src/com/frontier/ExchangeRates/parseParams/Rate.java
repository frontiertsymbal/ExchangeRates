package com.frontier.ExchangeRates.parseParams;

import java.io.Serializable;

public class Rate implements Serializable{

    private String baseCurrency;
    private String currency;
    private double saleRateNB;
    private double purchaseRateNB;
    private double saleRate;
    private double purchaseRate;

    public String getCurrency() {
        return currency;
    }

    public double getSaleRateNB() {
        return saleRateNB;
    }

    public double getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", currency='" + currency + '\'' +
                ", saleRateNB=" + saleRateNB +
                ", purchaseRateNB=" + purchaseRateNB +
                ", saleRate=" + saleRate +
                ", purchaseRate=" + purchaseRate +
                '}';
    }
}

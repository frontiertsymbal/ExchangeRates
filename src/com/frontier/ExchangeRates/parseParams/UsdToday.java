package com.frontier.ExchangeRates.parseParams;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class UsdToday implements Serializable{

    public UsdToday(String currency, double purchaseRate, double saleRate) {
        this.currency = currency;
        this.purchaseRate = purchaseRate;
        this.saleRate = saleRate;
    }

    @SerializedName("ccy")
    String currency;
    @SerializedName("buy")
    double purchaseRate;
    @SerializedName("sale")
    double saleRate;

    public String getCurrency() {
        return currency;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    public double getSaleRate() {
        return saleRate;
    }

    @Override
    public String toString() {
        return "UsdToday{" +
                "currency='" + currency + '\'' +
                ", purchaseRate=" + purchaseRate +
                ", saleRate=" + saleRate +
                '}';
    }
}

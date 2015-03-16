package com.frontier.ExchangeRates.util;

import com.frontier.ExchangeRates.parseParams.Rate;
import com.frontier.ExchangeRates.parseParams.Rates;

import java.util.ArrayList;
import java.util.List;

public class UsdList {

    private String date;
    private double saleRate;
    private double purchaseRate;
    private List<Rates> ratesList;

    public UsdList(List<Rates> ratesList) {
        this.ratesList = ratesList;
    }

    public UsdList(String date, double saleRate, double purchaseRate) {
        this.date = date;
        this.saleRate = saleRate;
        this.purchaseRate = purchaseRate;
    }

    //TODO reverse add.

    public List<UsdList> getUsdList() {
        List <UsdList> list = new ArrayList<>();
        for (int i = 0; i < ratesList.size(); i++) {
            List<Rate> listRate = ratesList.get(i).getRatesList();
            for (int j = 0; j < listRate.size(); j++) {
                if (listRate.get(j).getCurrency().equals("USD")) {
                    list.add(new UsdList(ratesList.get(i).getDate(), listRate.get(j).getSaleRate(), listRate.get(j).getPurchaseRate()));
                }
            }
        }
        return list;
    }

    public String getDate() {
        return date;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }
}

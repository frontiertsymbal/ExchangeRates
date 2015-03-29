package com.frontier.ExchangeRates.parseParams;

import java.io.Serializable;
import java.util.List;

public class UsdTodayList implements Serializable{

    private List<UsdToday> list;

    public List<UsdToday> getList() {
        return list;
    }
}

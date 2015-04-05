package com.frontier.ExchangeRates;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import com.frontier.ExchangeRates.parseParams.Rates;
import com.frontier.ExchangeRates.parseParams.RatesList;
import com.frontier.ExchangeRates.parseParams.TodayParser;
import com.frontier.ExchangeRates.parseParams.UsdToday;
import com.frontier.ExchangeRates.util.Const;
import com.frontier.ExchangeRates.util.DateBuilder;
import com.frontier.ExchangeRates.webKit.GetRequestToJSonString;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    /*TODO
        dateBuilder only weekdays
        today view
        design
        internet connection verify
        * load more rates list *

    */

    private List<String> dateList = DateBuilder.getDateList();
    private List<Rates> rates = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new RateLoad().execute();
    }

    private class RateLoad extends AsyncTask<Void, Void, List<Rates>> {

        @Override
        protected List<Rates> doInBackground(Void... params) {
            Gson gson = new GsonBuilder().create();
            String date;
            String archiveList;
            List<UsdToday> todayList;

            todayList = TodayParser.parse(GetRequestToJSonString.getString(Const.TODAY_URL));

            for (int i = 0; i < dateList.size(); i++) {
                String aDateList = dateList.get(i);
                date = aDateList;

                archiveList = GetRequestToJSonString.getString(Const.ARCHIVE_URL_BODY + aDateList);
                rates.add(new Rates(date, gson.fromJson(archiveList, RatesList.class).getRatesList()));
            }
            return rates;
        }

        @Override
        protected void onPostExecute(List<Rates> result) {

            Intent intent = new Intent(MainActivity.this, UsdRatesActivity.class);
//            intent.putExtra("today", (Serializable) usdToday);
            intent.putExtra("rates", (Serializable) result);
            startActivity(intent);
        }
    }

    //TODO verify internet connection;

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() == null;
    }
}

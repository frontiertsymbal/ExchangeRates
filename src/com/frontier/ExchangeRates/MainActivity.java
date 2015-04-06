package com.frontier.ExchangeRates;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
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
        today view
        design
        * load more rates list *
    */

    private List<String> dateList = DateBuilder.getDateList();
    private List<Rates> rates = new ArrayList<>();
    private List<UsdToday> todayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (!isOnline()) {
            new RateLoad().execute();
        } else {
            findViewById(R.id.waitProgressBar).setVisibility(View.INVISIBLE);
            (findViewById(R.id.splashScreen)).setVisibility(View.INVISIBLE);

            TextView noInternetConnection = new TextView(this);
            noInternetConnection.setText("No internet connection \nCheck the connection and restart the program");
            noInternetConnection.setTextSize(35);
            noInternetConnection.setGravity(Gravity.CENTER);

            ((FrameLayout) findViewById(R.id.mainFrame)).addView(noInternetConnection);
        }
    }

    private class RateLoad extends AsyncTask<Void, Void, List<Rates>> {

        @Override
        protected List<Rates> doInBackground(Void... params) {
            Gson gson = new GsonBuilder().create();
            String date;
            String archiveList;

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
            intent.putExtra("today", (Serializable) todayList);
            intent.putExtra("rates", (Serializable) result);
            startActivity(intent);
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() == null;
    }
}

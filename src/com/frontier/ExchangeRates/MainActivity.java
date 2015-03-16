package com.frontier.ExchangeRates;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.frontier.ExchangeRates.parseParams.Rates;
import com.frontier.ExchangeRates.parseParams.RatesList;
import com.frontier.ExchangeRates.util.DateBuilder;
import com.frontier.ExchangeRates.util.UsdList;
import com.frontier.ExchangeRates.util.UsdViewAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private HttpURLConnection urlConnection;
    private final String REQUEST_URL_BODY = "https://api.privatbank.ua/p24api/exchange_rates?json&date=";
    private List<String> dateList = DateBuilder.getDateList();
    private List<Rates> rates = new ArrayList<>();
    private final String LOG_TAG = "ExchangeRatesLog";
    private UsdViewAdapter adapter;
    private ImageView splash;
    private ListView ratesView;
    private ProgressBar progressBar;
    private TextView testText;
    private UsdList usdList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        splash = (ImageView) findViewById(R.id.splashScreen);
        testText = (TextView) findViewById(R.id.date);
        ratesView = (ListView) findViewById(R.id.ratesList);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new RateLoad().execute();

        ratesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = usdList.getUsdList().get(position).getDate();
                ArrayList<Rates> toPutRates = (ArrayList<Rates>) rates;
                Intent intent = new Intent(MainActivity.this, RatesActivity.class);

                intent.putExtra("date", date);
                intent.putExtra("ratesList", toPutRates);

                startActivity(intent);
            }
        });
    }

    private class RateLoad extends AsyncTask<Void, Void, List<Rates>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Rates> doInBackground(Void... params) {
            Gson gson = new GsonBuilder().create();
            String date;
            for (String aDateList : dateList) {
                try {
                    date = aDateList;
                    URL url = new URL(REQUEST_URL_BODY + aDateList);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode() == 200) {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        rates.add(new Rates(date, gson.fromJson(readStream(in), RatesList.class).getRatesList()));
                    } else {
                        Log.e(LOG_TAG, "The server responded with " + urlConnection.getResponseCode());
                        return null;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            }

            return rates;
        }

        @Override
        protected void onPostExecute(List<Rates> result) {
            if (result != null) {

                splash.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                testText.setVisibility(View.VISIBLE);
                ratesView.setVisibility(View.VISIBLE);

                usdList = new UsdList(result);
                adapter = new UsdViewAdapter(MainActivity.this, usdList.getUsdList());

                ratesView.setAdapter(adapter);

            } else {
                Toast.makeText(MainActivity.this, "The data received from the server " +
                        "do not match the query needs.", Toast.LENGTH_LONG).show();
            }
        }

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            String request = "";
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    request += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return request;
        }
    }
}

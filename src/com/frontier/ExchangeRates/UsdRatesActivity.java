package com.frontier.ExchangeRates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.frontier.ExchangeRates.Adapters.UsdViewAdapter;
import com.frontier.ExchangeRates.parseParams.Rates;
import com.frontier.ExchangeRates.parseParams.UsdToday;
import com.frontier.ExchangeRates.util.UsdList;

import java.util.ArrayList;
import java.util.List;

public class UsdRatesActivity extends Activity {


    private UsdViewAdapter adapter;
    private ListView ratesView;
    private UsdList usdList;
    private List<Rates> rates = new ArrayList<>();
    private List<UsdToday> usdToday = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        rates = (List<Rates>) getIntent().getSerializableExtra("rates");
        usdToday = (List<UsdToday>) getIntent().getSerializableExtra("today");

        ratesView = (ListView) findViewById(R.id.ratesList);

        if (rates != null && usdToday != null) {

            usdList = new UsdList(rates);
            adapter = new UsdViewAdapter(UsdRatesActivity.this, usdList.getUsdList());

            addHeader();

            ratesView.setAdapter(adapter);

        } else {
            Toast.makeText(UsdRatesActivity.this, "The data received from the server " +
                    "do not match the query needs.", Toast.LENGTH_LONG).show();
        }

        ratesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = usdList.getUsdList().get(position - 1).getDate();
                ArrayList<Rates> toPutRates = (ArrayList<Rates>) rates;
                Intent intent = new Intent(UsdRatesActivity.this, RatesActivity.class);

                intent.putExtra("date", date);
                intent.putExtra("ratesList", toPutRates);

                startActivity(intent);
            }
        });


    }

    private void addHeader() {
        int usd = 0;
        for (int i = 0; i < usdToday.size(); i++) {
            if (usdToday.get(i).getCurrency().equals("USD")) {
                usd = i;
                break;
            }
        }
        View today = getLayoutInflater().inflate(R.layout.today_header, null);
        ((TextView) today.findViewById(R.id.todayPurchaseRateTextView)).setText(String.valueOf(usdToday.get(usd).getPurchaseRate()));
        ((TextView) today.findViewById(R.id.todaySaleRateTextView)).setText(String.valueOf(usdToday.get(usd).getSaleRate()));
        ratesView.addHeaderView(today);
    }


}

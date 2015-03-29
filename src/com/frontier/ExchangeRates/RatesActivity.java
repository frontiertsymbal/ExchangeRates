package com.frontier.ExchangeRates;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.frontier.ExchangeRates.parseParams.Rate;
import com.frontier.ExchangeRates.parseParams.Rates;
import com.frontier.ExchangeRates.Adapters.RateAdapter;

import java.util.ArrayList;
import java.util.List;

public class RatesActivity extends Activity {

    private ListView ratesListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rates_layout);

        String date = getIntent().getStringExtra("date");
        ArrayList<Rates> ratesListGet = (ArrayList<Rates>) getIntent().getSerializableExtra("ratesList");
        List<Rate> rates = new ArrayList<>();
        for (int i = 0; i < ratesListGet.size(); i++) {
            if (ratesListGet.get(i).getDate().equals(date)) {
                rates = ratesListGet.get(i).getRatesList();
            }
        }
        ratesListView = (ListView) findViewById(R.id.ratesActivityList);
        RateAdapter adapter = new RateAdapter(RatesActivity.this, rates);
        ratesListView.setAdapter(adapter);


        TextView textView = (TextView) findViewById(R.id.ratesActivityDate);
        textView.setText(date);

    }
}

package com.frontier.ExchangeRates.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.frontier.ExchangeRates.R;
import com.frontier.ExchangeRates.parseParams.Rate;
import com.frontier.ExchangeRates.util.UsdList;

import java.util.List;

public class UsdViewAdapter extends ArrayAdapter<UsdList> {

    private final LayoutInflater inflater;

    public UsdViewAdapter(Context context, List<UsdList> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.usd_rate, parent, false);

            TextView date = (TextView) convertView.findViewById(R.id.dateTextView);
            TextView saleRateTextView = (TextView) convertView.findViewById(R.id.saleRateTextView);
            TextView purchaseRateTextView = (TextView) convertView.findViewById(R.id.purchaseRateTextView);


            date.setText(getItem(position).getDate());
            saleRateTextView.setText(String.valueOf(getItem(position).getSaleRate()));
            purchaseRateTextView.setText(String.valueOf(getItem(position).getPurchaseRate()));

        }

        UsdList usd = getItem(position);

        TextView date = (TextView) convertView.findViewById(R.id.dateTextView);
        TextView saleRateTextView = (TextView) convertView.findViewById(R.id.saleRateTextView);
        TextView purchaseRateTextView = (TextView) convertView.findViewById(R.id.purchaseRateTextView);


        date.setText(usd.getDate());
        saleRateTextView.setText(String.valueOf(usd.getSaleRate()));
        purchaseRateTextView.setText(String.valueOf(usd.getPurchaseRate()));

        return convertView;
    }
}

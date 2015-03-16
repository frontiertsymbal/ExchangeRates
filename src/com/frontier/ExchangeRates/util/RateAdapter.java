package com.frontier.ExchangeRates.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.frontier.ExchangeRates.R;
import com.frontier.ExchangeRates.parseParams.Rate;

import java.util.List;

public class RateAdapter extends ArrayAdapter<Rate> {

    private final LayoutInflater inflater;


    public RateAdapter(Context context, List<Rate> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.rate_item, parent, false);

            TextView currency = (TextView) convertView.findViewById(R.id.currency);
            TextView saleRateNB = (TextView) convertView.findViewById(R.id.saleRateNB);
            TextView saleRate = (TextView) convertView.findViewById(R.id.saleRate);
            TextView purchaseRateNB = (TextView) convertView.findViewById(R.id.purchaseRateNB);
            TextView purchaseRate = (TextView) convertView.findViewById(R.id.purchaseRate);

            currency.setText(getItem(position).getCurrency());
            saleRateNB.setText(String.valueOf(getItem(position).getSaleRateNB()));
            saleRate.setText(String.valueOf(getItem(position).getSaleRate()));
            purchaseRateNB.setText(String.valueOf(getItem(position).getPurchaseRateNB()));
            purchaseRate.setText(String.valueOf(getItem(position).getPurchaseRate()));

        }

        Rate item = getItem(position);

        TextView currency = (TextView) convertView.findViewById(R.id.currency);
        TextView saleRateNB = (TextView) convertView.findViewById(R.id.saleRateNB);
        TextView saleRate = (TextView) convertView.findViewById(R.id.saleRate);
        TextView purchaseRateNB = (TextView) convertView.findViewById(R.id.purchaseRateNB);
        TextView purchaseRate = (TextView) convertView.findViewById(R.id.purchaseRate);

        currency.setText(item.getCurrency());
        saleRateNB.setText(String.valueOf(item.getSaleRateNB()));
        saleRate.setText(String.valueOf(item.getSaleRate()));
        purchaseRateNB.setText(String.valueOf(item.getPurchaseRateNB()));
        purchaseRate.setText(String.valueOf(item.getPurchaseRate()));

        return convertView;
    }
}

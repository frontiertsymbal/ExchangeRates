package com.frontier.ExchangeRates.webKit;

import android.util.Log;
import com.frontier.ExchangeRates.util.Const;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlConnectionGetToJSonList {

    private static HttpURLConnection urlConnection;
    private static String JSonStringList;

    public static String getList(String urlString) {
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if (urlConnection.getResponseCode() == 200) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                JSonStringList = readStream(in);
                in.close();
            } else {
                Log.e(Const.LOG_TAG, "The server responded with " + urlConnection.getResponseCode());
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return JSonStringList;
    }



    private static String readStream(InputStream in) {
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
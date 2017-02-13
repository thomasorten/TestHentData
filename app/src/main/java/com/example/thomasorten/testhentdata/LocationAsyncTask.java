package com.example.thomasorten.testhentdata;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by thomasorten on 09.02.2017.
 */

public class LocationAsyncTask extends AsyncTask<String, Integer, Long>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Long doInBackground(String... params) {
        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            String resultString = downloadUrl(url);
            if (resultString != null) {
                Log.d("Svar", resultString);
            }
        } catch(Exception e) {
            Log.d("Feil", e.toString());
        }

        return null;
    }
    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
    }

    private String downloadUrl(URL url) throws IOException {
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);
            httpConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}

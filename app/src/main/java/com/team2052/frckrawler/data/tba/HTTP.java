package com.team2052.frckrawler.data.tba;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HTTP {
    public static final String TBA_APP_HEADER = "frc2052:frckrawler-scouting-system:v3";
    private static OkHttpClient client;

    public static OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient();
        }
        return client;
    }

    public static Response getResponse(String url) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("X-TBA-App-Id", TBA_APP_HEADER);
        Request request = requestBuilder.build();
        try {
            return getClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dataFromResponse(Response response) {
        try {
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
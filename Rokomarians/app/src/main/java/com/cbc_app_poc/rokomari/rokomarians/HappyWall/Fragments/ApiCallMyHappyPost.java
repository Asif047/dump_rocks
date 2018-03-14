package com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments;


import com.cbc_app_poc.rokomari.rokomarians.Interfaces.MyHappyPostApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallMyHappyPost implements MyHappyPostApi{
    @Override
    public String GET(OkHttpClient client, String url, String account_id, int id) throws IOException {

            Request request = new Request.Builder()
                    .url(url)
                    .header("Authorization", account_id)
                    .header("id", ""+id)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();

    }
}

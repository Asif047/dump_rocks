package com.cbc_app_poc.rokomari.rokomarians.HappyWall;


import com.cbc_app_poc.rokomari.rokomarians.Interfaces.HappySeeAllApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallSeeAll implements HappySeeAllApi{
    @Override
    public String GET(OkHttpClient client, String url, String account_id) throws IOException {
        Request request = new Request.Builder()
                                .url(url)
                                .header("Authorization", account_id)
                                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.ApiCalls;


import com.cbc_app_poc.rokomari.rokomarians.Interfaces.IdeaApi;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallidea implements IdeaApi {

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

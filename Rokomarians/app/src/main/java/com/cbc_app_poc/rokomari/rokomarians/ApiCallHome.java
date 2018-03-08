package com.cbc_app_poc.rokomari.rokomarians;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiCallHome {

    public String GET(OkHttpClient client, String url, String email, String account_id) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", account_id)
                .header("email", email)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

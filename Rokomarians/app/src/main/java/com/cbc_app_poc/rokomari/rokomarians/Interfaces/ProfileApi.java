package com.cbc_app_poc.rokomari.rokomarians.Interfaces;


import java.io.IOException;

import okhttp3.OkHttpClient;

public interface ProfileApi {

    public String GET (OkHttpClient client, String url, String account_id) throws IOException;

}

package com.cbc_app_poc.rokomari.rokomarians.Interfaces;


import java.io.IOException;

import okhttp3.OkHttpClient;

public interface HappyCommentApi {

    public String GET(OkHttpClient client, String url, String account_id, int post_id) throws IOException;
}

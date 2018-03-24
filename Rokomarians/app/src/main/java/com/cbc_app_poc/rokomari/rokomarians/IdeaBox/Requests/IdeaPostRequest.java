package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities.IdeaBoxActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IdeaPostRequest {

    private String responsePost = "";
    private String response_status = "";
    private Context context;

    public IdeaPostRequest(Context context) {
        this.context = context;
    }

    public void postData(String title, String idea, String account_id, String url){
        MediaType JSON = MediaType.parse("application/json; charset= utf-8");
        Map<String, String> params = new HashMap<String, String>();

        params.put("title", title);
        params.put("idea", idea);

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                                    .url(url)
                                    .post(body)
                                    .header("Authorization", account_id)
                                    .addHeader("Content-Type", "application/json")
                                    .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                responsePost = ""+response.code();
                Log.e("response_post_idea: ", responsePost);

                if(responsePost.equals("200")) {
                    Intent intent = new Intent(context, IdeaBoxActivity.class);
                    context.startActivity(intent);
                }



            }
        });

    }

}

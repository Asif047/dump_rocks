package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities.IdeaBoxActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IdeaDeleteRequest {

    private String responsePost = "";
    private String response_status = "";
    private Context context;

    public IdeaDeleteRequest(Context context) {
        this.context = context;
    }

    public void deleteData(int elementId, String account_id, String url){

        MediaType JSON = MediaType.parse("application/json; charset= utf-8");
        Map<String, Integer> params = new HashMap<String, Integer>();

        params.put("elementId", elementId);

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(url)
                .delete(body)
                .header("Authorization", account_id)
                .header("id",""+elementId)
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

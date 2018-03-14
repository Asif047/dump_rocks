package com.cbc_app_poc.rokomari.rokomarians.HappyWall;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
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

public class HappyPostRequest {

    private String responsePost = "";
    private String response_status = "";
    private Context context;

    public HappyPostRequest(Context context){
        this.context = context;
    }

    public void postData(String details, String url, String account_id){
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();

        params.put("details", ""+details);

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                                .url(url)
                                .post(body)
                                .header("Authorization", account_id)
                                .addHeader("content-type", "application/json; charset=utf-8")
                                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                responsePost = response.code()+"";
                Log.e("###response_code", responsePost);

                if(responsePost.equals("200")) {

                    Intent intent = new Intent(context, HappyWallActivity.class);

                    context.startActivity(intent);
                }
                else {
                    ((Activity)context).runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(context,"Update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

}

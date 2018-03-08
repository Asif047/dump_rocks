package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelParticipate;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;

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

public class ParticipatePostRequest {

    private String responsePost = "";
    private Context context;

    public ParticipatePostRequest(Context context) {
        this.context = context;
    }


    public void postData(String url, ModelParticipate modelParticipate, final String account_id) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,   "{\r\n    \"recreationHour\": {\r\n " +
                "       \"details\" : \""+modelParticipate.getRecreationHour().getDetails()+"\"," +
                "\r\n\t\t\t\t\"event\" : \""+modelParticipate.getRecreationHour().getEvent()+"\"\r\n    }," +
                "\r\n\r\n    \"userId\": "+modelParticipate.getUserId()+"\r\n}");

        Request request = new Request.Builder()
                .url(url+"recreation/participate")
                .post(body)
                .header("Authorization", account_id)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.e("response", call.request().body().toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                if (RegisterActivity.pDialog.isShowing())
//                    RegisterActivity.pDialog.dismiss();

                Log.e("###ID:", ""+account_id);
                responsePost = response.code()+"";
                Log.e("###response_code", responsePost);



                if(responsePost.equals("200")) {
                    Intent intent = new Intent(context, HomeActivity.class);

                    context.startActivity(intent);
                } else
                {
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

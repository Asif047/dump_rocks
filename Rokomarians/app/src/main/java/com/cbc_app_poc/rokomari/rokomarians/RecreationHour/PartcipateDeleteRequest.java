package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PartcipateDeleteRequest {

    private String responsePost = "";
    private Context context;

    public PartcipateDeleteRequest(Context context) {
        this.context = context;
    }


    public void deleteData(String url, final String account_id) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,    "");

        Request request = new Request.Builder()
                .url(url+"participation/cancel-participation")
                .delete(body)
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
                            Toast.makeText(context,"Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });

    }

}

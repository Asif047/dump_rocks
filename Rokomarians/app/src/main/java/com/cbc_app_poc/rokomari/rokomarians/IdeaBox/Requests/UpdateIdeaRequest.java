package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities.IdeaBoxActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpdateIdeaRequest {

    private String responsePost = "";
    private Context context;

    //private PostActivity postActivity=new PostActivity();

    public UpdateIdeaRequest(Context context) {
        this.context = context;
    }


    public void putData(String url, int idea_id, String title, String idea, String account_id) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,  "{\r\n\t\"id\":"+idea_id+"," +
                "\r\n\t\"title\":\""+title+"\"," +
                "\r\n\t\"idea\":\""+idea+"\"\r\n}");


        Request request = new Request.Builder()
                .url(url)
                .put(body)
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

                responsePost = response.code() + "";
                Log.e("###response_code", responsePost);


                if (responsePost.equals("200")) {
                    Intent intent = new Intent(context, IdeaBoxActivity.class);

                    context.startActivity(intent);
                } else {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(context, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });

    }

}

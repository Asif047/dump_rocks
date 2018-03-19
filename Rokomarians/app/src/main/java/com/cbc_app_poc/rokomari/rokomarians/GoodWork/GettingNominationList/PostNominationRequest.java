package com.cbc_app_poc.rokomari.rokomarians.GoodWork.GettingNominationList;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GoodWorkActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyWallActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostNominationRequest {

    private String responsePost = "";
    private String response_status;
    private Context context;
    private Role.User user;
    //private PostActivity postActivity=new PostActivity();

    public PostNominationRequest(Context context) {
        this.context = context;
    }

    public void postData(String url,int nominated_id, String reason, String account_id) {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,   "{\r\n\t\r\n\t\"nominatedUserId\" : "+nominated_id+"," +
                "\r\n\t\"reason\" : \" "+reason+"\"\r\n\t\r\n}");

        Request request = new Request.Builder()
                .url(url+"nomination/new-nomination")
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

                responsePost = response.code()+"";
                Log.e("###response_code", responsePost);

                if(responsePost.equals("200")) {

                    Intent intent = new Intent(context, GoodWorkActivity.class);

                    context.startActivity(intent);
                }
                else
                {
                    ((Activity)context).runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(context,"Nomination not submitted", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });

    }

}

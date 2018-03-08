package com.cbc_app_poc.rokomari.rokomarians;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.Profile.ProfileActivity;
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

import static android.content.Context.MODE_PRIVATE;

public class LogInMyPostRequest {

    private String responsePost = "";
    private String response_status;
    private Context context;

    String logInResponse;
    private String account_token = "";
    //private PostActivity postActivity=new PostActivity();

    public LogInMyPostRequest(Context context) {
        this.context = context;
    }


    public void postData(String url, final String email, String password) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", "" + email);
        params.put("password", "" + password);

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(url+"login")
                .post(body)
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
                Log.e("###response_code:", responsePost);

                if(responsePost.equals("200"))
                {
                    // Toast.makeText(context,response_status,Toast.LENGTH_SHORT).show();
                    logInResponse="logged In";

                    account_token = response.headers().get("Authorization");

                    SharedPreferences.Editor editor = context.getSharedPreferences("Profile_PREF", MODE_PRIVATE).edit();
                    editor.putString("account_id", account_token);
                    editor.putString("email", email);
                    editor.apply();

                    Intent intent = new Intent(context, HomeActivity.class);

                    context.startActivity(intent);
                }

                else
                {
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //show the response body
                            Toast.makeText(context,"Incorrent Email or Password", Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }

        });


    }


}

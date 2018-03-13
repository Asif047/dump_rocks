package com.cbc_app_poc.rokomari.rokomarians.Register;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.LogInActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
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

public class RegisterMyPostRequest extends Application {

    private String responsePost = "";
    private String response_status;
    private Context context;
    private Role.User user;
    //private PostActivity postActivity=new PostActivity();

    public RegisterMyPostRequest(Context context) {
        this.context = context;
    }


    public void postData(String url,Role.User user) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, "{\r\n    \"user\": " +
                "{\r\n        \"firstName\": \""+user.getFirstName()+"\",\r\n " +
                "       \"lastName\": \""+user.getLastName()+"\",\r\n       " +
                " \"email\": \""+user.getEmail()+"\",\r\n        " +
                "\"password\": \""+user.getPassword()+"\",\r\n        " +
                "\"phone\": \""+user.getPhone()+"\"\r\n   ,  " +
                "\"imagePath\": \""+null+"\"\r\n   ,  " +
                "\"status\": \""+user.getStatus()+"\"\r\n     " +
                "\r\n    },\r\n    \"role\": [\r\n        " +
                "\"USER\"\r\n    ]\r\n}");


        Request request = new Request.Builder()
                .url(url+"users/new-user")
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
                Log.e("###response_code", responsePost);



                if(responsePost.equals("201"))
                {
                    Intent intent = new Intent(context, LogInActivity.class);

                    context.startActivity(intent);
                }
                else
                {
                    ((Activity)context).runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            Toast.makeText(context,"Regitration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }

        });

    }

}

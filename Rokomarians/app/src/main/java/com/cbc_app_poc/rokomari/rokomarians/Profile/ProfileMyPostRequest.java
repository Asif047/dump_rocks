package com.cbc_app_poc.rokomari.rokomarians.Profile;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cbc_app_poc.rokomari.rokomarians.Model.User;
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

public class ProfileMyPostRequest {


    private String responsePost = "";
    private String first_name = "", last_name = "", image = "", email_profile = "", phone = "", address = "",
            hometown = "", education = "", hobbies = "", team = "", designation = "", work = "", joining_date = "";
    private int id;
    private Context context;
    private User user;


    //private PostActivity postActivity=new PostActivity();

    public ProfileMyPostRequest(Context context) {
        this.context = context;
    }

    public void postData(String url, final String email, String account_id) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", "" + email);


        JSONObject parameter = null;
        parameter = new JSONObject(params);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(url+"users/profile")
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

                responsePost = response.body().string();
                Log.e("response", responsePost);

                Gson gson = new Gson();
                Type type = new TypeToken<User>() {
                }.getType();

                user = gson.fromJson(responsePost, type);
                Log.e("####response", user.getFirstName());
                id = user.getId();
                first_name = user.getFirstName();
                last_name = user.getLastName();
                image = user.getImagePath();
                email_profile = user.getEmail();
                phone = user.getPhone();
                address = user.getUserPersonalInfo().getAddress();
                hometown = user.getUserPersonalInfo().getHomeTown();
                education = user.getUserPersonalInfo().getEducation();
                hobbies = user.getUserPersonalInfo().getHobbies();
                team = user.getUserOfficeInfo().getTeam();
                designation = user.getUserOfficeInfo().getDesignation();
                work = user.getUserOfficeInfo().getWork();
                joining_date = user.getUserOfficeInfo().getJoiningDate();


                Log.e("RESPONSE POST: ", "" + first_name);

                // Toast.makeText(context,response_status,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("first_name", first_name);
                intent.putExtra("last_name", last_name);
                intent.putExtra("image", image);
                intent.putExtra("email_profile", email_profile);
                intent.putExtra("phone", phone);
                intent.putExtra("address", address);
                intent.putExtra("hometown", hometown);
                intent.putExtra("education", education);
                intent.putExtra("hobbies", hobbies);
                intent.putExtra("team", team);
                intent.putExtra("designation", designation);
                intent.putExtra("work", work);
                intent.putExtra("joining_date", joining_date);


                context.startActivity(intent);

            }

        });


    }

}

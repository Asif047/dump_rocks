package com.cbc_app_poc.rokomari.rokomarians.Profile;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.LogInActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

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

public class EditProfilePostRequest extends Application {

    private String responsePost = "";
    private String response_status;
    private Context context;
    private Role.User user;
    //private PostActivity postActivity=new PostActivity();

    private String account_id="", id="";


    public EditProfilePostRequest(Context context) {
        this.context = context;
    }


    public void postData(String url, Role.User user, String account_id, final int id) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,  "{\r\n    \"user\": {\r\n" +
                "  \"firstName\": \""+user.getFirstName()+"\",\r\n " +
                " \"lastName\": \""+user.getLastName()+"\",\r\n       " +
                " \"phone\": \""+user.getPhone()+"\",\r\n       " +
                " \"imagePath\": \""+user.getImagePath()+"\",\r\n        " +
                "\"status\": \"ACTIVE\",\r\n        " +
                "\"userPersonalInfo\": {\r\n           " +
                " \"homeTown\": \""+user.getUserPersonalInfo().getHomeTown()+"\",\r\n           " +
                " \"education\": \""+user.getUserPersonalInfo().getEducation()+"\",\r\n           " +
                " \"hobbies\": \""+user.getUserPersonalInfo().getHobbies()+"\",\r\n            " +
                "\"address\": \""+user.getUserPersonalInfo().getAddress()+"\"\r\n        }," +
                "\r\n        \"userOfficeInfo\": {\r\n            " +
                "\"team\": \""+user.getUserOfficeInfo().getTeam()+"\",\r\n     " +
                "\"designation\": \""+user.getUserOfficeInfo().getDesignation()+"\",\r\n        " +
                "  \"work\": \""+user.getUserOfficeInfo().getWork()+"\",\r\n        " +
                "\"joiningDate\": \""+user.getUserOfficeInfo().getJoiningDate()+"\"\r\n    " +
                "   }\r\n    },\r\n    \"role\": [\r\n      " +
                " \"USER\"\r\n    ]\r\n}");


        Request request = new Request.Builder()
                .url(url+"users/"+id)
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

                Log.e("###ID:", ""+id);
                responsePost = response.code()+"";
                Log.e("###response_code", responsePost);

                if(responsePost.equals("200")) {

                    Intent intent = new Intent(context, HomeActivity.class);

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

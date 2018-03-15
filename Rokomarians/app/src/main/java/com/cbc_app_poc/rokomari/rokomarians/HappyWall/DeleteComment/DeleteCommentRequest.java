package com.cbc_app_poc.rokomari.rokomarians.HappyWall.DeleteComment;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

public class DeleteCommentRequest {

    private String responsePost = "";
    private String response_status = "";
    private Context context;
    FragmentManager fragmentManager;

    public DeleteCommentRequest(Context context){
        this.context = context;
    }

    public void deleteData( String url, int post_id, int comment_id, String account_id){
        MediaType JSON = MediaType.parse("application/json; charset= utf-8");
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("commentId", comment_id);
        params.put("postId", post_id);

        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url(url+"comments/delete-own-comment")
                .delete(body)
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
                Log.e("###response_code: ", responsePost);

                if(responsePost.equals("200")) {

                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
//                else {
//                    ((Activity)context).runOnUiThread(new Runnable()
//                    {
//                        public void run()
//                        {
//                            Toast.makeText(context,"You can't delete it", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }
            }
        });
    }

}

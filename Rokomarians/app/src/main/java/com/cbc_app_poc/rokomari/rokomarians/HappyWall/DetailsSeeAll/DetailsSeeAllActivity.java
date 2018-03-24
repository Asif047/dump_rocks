package com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsSeeAll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DeleteHappyPost.DeleteHappyPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsMyHappyPostActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.ApiCallMyHappyPost;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyCommentPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.LikeHappyPost.LikeHappyPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.RecyclerAdapterSeeAll;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelCommentsHappyPost;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelHappySeeAll;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;

public class DetailsSeeAllActivity extends AppCompatActivity {

    private SweetAlertDialog pDialog;
    private String BASE_URL ="http://192.168.11.231:9090/";
    private String path, path_comment, response, response_comment;
    private OkHttpClient client;
    private ModelHappySeeAll modelHappySeeAll;
    private ApiCallMyHappyPost apiCallMyHappyPost;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = "", name="";
    private int post_id = 0;
    private TextView tvname, tvDetails, tvLikeNumbers;
    private EditText etComment;
    private Button btnPostComment;
    private ImageView ivLikes;

    private LikeHappyPostRequest likeHappyPostRequest;
    private HappyCommentPostRequest happyCommentPostRequest;

    //Happy get comments starts
    private ModelCommentsHappyPost[] modelCommentsHappyPosts;
    private ApiCallHappyComments apiCallHappyComments;
    private List<ModelCommentsHappyPost> data;
    private RecyclerView recyclerView;
    private RecyclerAdapterHappyComments recyclerAdapterHappyComments;
    private RecyclerView.LayoutManager layoutManager;
    //Happy get comments ends


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_see_all);


        final Intent intent = getIntent();
        post_id = intent.getIntExtra("happy_post_id", 0);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);
        String restoredName = prefs.getString("user_name", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        if (restoredName != null) {
            name = prefs.getString("user_name", "No account defined");
        }
        //getting account id ends

        likeHappyPostRequest = new LikeHappyPostRequest(this);
        happyCommentPostRequest = new HappyCommentPostRequest(this);

        apiCallMyHappyPost = new ApiCallMyHappyPost();
        apiCallHappyComments = new ApiCallHappyComments();
        data = new ArrayList<>();

        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        tvname = findViewById(R.id.textview_name_happy_post);
        tvDetails = findViewById(R.id.textview_details_happy_post);
        tvLikeNumbers = findViewById(R.id.textview_likes_happy_post);
        ivLikes = findViewById(R.id.imageview_like_details_happy_post);
        etComment = findViewById(R.id.edittext_comment_happy_post);
        btnPostComment = findViewById(R.id.button_post_comment_happy_post);


        //happy get comments starts
        recyclerView =findViewById(R.id.recyclerview_details_happy_post);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        //happy get comments ends


        try{
            path = BASE_URL + "happy-post/get-post";
            path_comment = BASE_URL + "comments/";
            if(!myNetworkCheck.isConnected(this)){
                showAlert.showWarningNetDetailsHappyPostActivity();
            }
            else {
                new DetailsSeeAllActivity.GetDataFromServer().execute();
                new DetailsSeeAllActivity.GetCommentsFromServer().execute();
            }

        } catch (Exception e){

        }




        ivLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myNetworkCheck.isConnected(DetailsSeeAllActivity.this)){
                    showAlert.showWarningNetDetailsHappyPostActivity();
                } else {
                    likeHappyPostRequest.postData(BASE_URL, post_id, account_id);
                }

            }
        });

        btnPostComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                happyCommentPostRequest.postData(BASE_URL,post_id, etComment.getText().toString(),
                        account_id);
            }
        });



    }


    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#26A65B"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e){

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try{
                if(pDialog.isShowing()){
                    pDialog.dismiss();
                }

                tvname.setText(modelHappySeeAll.getName());
                tvDetails.setText(modelHappySeeAll.getDetails());
                tvLikeNumbers.setText(""+modelHappySeeAll.getNumberOfLikes());


                if(modelHappySeeAll.getLiked()== true){
                    ivLikes.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_blue));
                } else {
                    ivLikes.setImageDrawable(getResources().getDrawable(R.drawable.ic_good_work));
                }
            } catch (Exception e){

            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                client = new OkHttpClient();
                response = apiCallMyHappyPost.GET(client,path, account_id, post_id);
                Gson gson = new Gson();
                Log.e("#MY_POST:", response);
                modelHappySeeAll = gson.fromJson(response, ModelHappySeeAll.class);
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }


    private class GetCommentsFromServer extends AsyncTask< Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#26A65B"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e){

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(pDialog.isShowing()){
                pDialog.show();
            }

            recyclerAdapterHappyComments = new RecyclerAdapterHappyComments(DetailsSeeAllActivity.this,
                    data, account_id, name);
            recyclerView.setAdapter(recyclerAdapterHappyComments);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response_comment = apiCallHappyComments.GET(client, path_comment, account_id, post_id);
                Log.e("##GET_COMMENTS: ", response_comment);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<ModelCommentsHappyPost>>(){

                }.getType();

                Collection<ModelCommentsHappyPost> enums = gson.fromJson(response_comment, type);
                modelCommentsHappyPosts = enums.toArray(new ModelCommentsHappyPost[enums.size()]);

                if(data.isEmpty()){
                    for(int i = 0; i < enums.size(); i++){
                        data.add(modelCommentsHappyPosts[i]);
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }
    }


}

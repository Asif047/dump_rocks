package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.PostLikeGoodWork;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Adapter.RecyclerAdapterIdeas;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.ApiCalls.ApiCallDetailsIdea;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests.LikeIdeaRequest;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelIdea;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.OkHttpClient;

public class DetailsIdeaActivity extends AppCompatActivity {

    private SweetAlertDialog pDialog;
    private String BASE_URL = "http://192.168.11.231:9090/";
    private String BASE_URL_LIKE_IDEA = "http://192.168.11.231:9090/like/like-idea";
    private String path, response;
    private OkHttpClient client;
    private ModelIdea modelIdea;
    private ApiCallDetailsIdea apiCallDetailsIdea;

    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;
    private String account_id = "";
    private int idea_id;

    private ImageView ivIdeaMan, ivLike;
    private TextView tvName, tvTitle, tvIdeaDetails, tvNumOfLikes;

    private LikeIdeaRequest likeIdeaRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_idea);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        ivIdeaMan = findViewById(R.id.imageview_idea_man_details);
        ivLike = findViewById(R.id.imageview_like_idea_details);
        tvTitle = findViewById(R.id.textview_title_idea_details);
        tvName = findViewById(R.id.textview_name_idea_man_details);
        tvIdeaDetails = findViewById(R.id.textview_idea_details);
        tvNumOfLikes = findViewById(R.id.textview_num_like_idea_details);

        likeIdeaRequest = new LikeIdeaRequest(this);

        final Intent intent = getIntent();
        idea_id = intent.getIntExtra("idea_id", 0);

        apiCallDetailsIdea = new ApiCallDetailsIdea();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);


        try{
            path = BASE_URL + "idea-box/idea";
            new GetDataFromServer().execute();
        } catch (Exception e){

        }

        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeIdeaRequest.postData(idea_id, account_id, BASE_URL_LIKE_IDEA);
            }
        });

    }



    private class GetDataFromServer extends AsyncTask< Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
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

            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }

            tvName.setText(modelIdea.getFirstName() +" "+ modelIdea.getLastName());
            tvTitle.setText(modelIdea.getTitle());
            tvIdeaDetails.setText(modelIdea.getIdea());
            tvNumOfLikes.setText(""+modelIdea.getNumberOfLikes());

            Picasso.get().load(modelIdea.getImage()).into(ivIdeaMan);


            if(modelIdea.getLiked()== true) {
                ivLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_like_blue));
            } else {
                ivLike.setImageDrawable(getResources().getDrawable(R.drawable.ic_good_work));
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response = apiCallDetailsIdea.GET(client, path, account_id, idea_id);
                Log.e("##GET_IDEAS: ", response);
                Gson gson = new Gson();

                modelIdea = gson.fromJson(response, ModelIdea.class);

            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }



}

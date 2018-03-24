package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.ApiCalls.ApiCallDetailsIdea;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests.IdeaDeleteRequest;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests.LikeIdeaRequest;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelIdea;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class DetailsMyIdeaActivity extends AppCompatActivity {

    private SweetAlertDialog pDialog;
    private String BASE_URL = "http://192.168.11.231:9090/";
    private String BASE_URL_LIKE_IDEA = "http://192.168.11.231:9090/like/like-idea";
    private String BASE_URL_DELETE = "http://192.168.11.231:9090/idea-box/";
    private String path, response;
    private OkHttpClient client;
    private ModelIdea modelIdea;
    private ApiCallDetailsIdea apiCallDetailsIdea;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private TextView tvName, tvTitle, tvDetails, tvNumOfLikes;
    private ImageView ivIdeaMan, ivLike;
    private String account_id = "";
    private int idea_id;

    private LikeIdeaRequest likeIdeaRequest;
    private IdeaDeleteRequest ideaDeleteRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my_idea);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        //getting account id ends


        likeIdeaRequest = new LikeIdeaRequest(this);
        ideaDeleteRequest = new IdeaDeleteRequest(this);

        final Intent intent = getIntent();
        idea_id = intent.getIntExtra("my_idea_id", 0);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        apiCallDetailsIdea = new ApiCallDetailsIdea();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        tvName = findViewById(R.id.textview_name_idea_man_details_my_idea);
        tvTitle = findViewById(R.id.textview_title_details_my_idea);
        tvDetails = findViewById(R.id.textview_idea_details_details_my_idea);
        tvNumOfLikes = findViewById(R.id.textview_num_like_idea_details_my_idea);

        ivIdeaMan = findViewById(R.id.imageview_idea_man_details_my_idea);
        ivLike = findViewById(R.id.imageview_like_idea_details_my_idea);

        try {
            path = BASE_URL + "idea-box/idea";
            new DetailsMyIdeaActivity.GetDataFromServer().execute();
        } catch (Exception e) {

        }

        ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeIdeaRequest.postData(idea_id, account_id, BASE_URL_LIKE_IDEA);
            }
        });



    }

    public void updateMyIdea(MenuItem item) {
        Intent intent = new Intent(DetailsMyIdeaActivity.this, EditMyIdeaActivity.class);
        intent.putExtra("idea_id", idea_id);
        startActivity(intent);
    }

    public void deleteMyIdea(MenuItem item) {
        ideaDeleteRequest.deleteData(idea_id, account_id, BASE_URL_DELETE);
    }


    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#26A65B"));
                pDialog.setTitleText("Loading");
                pDialog.setCancelable(false);
                pDialog.show();
            } catch (Exception e) {

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            tvName.setText(modelIdea.getFirstName() + " " + modelIdea.getLastName());
            tvTitle.setText(modelIdea.getTitle());
            tvDetails.setText(modelIdea.getTitle());
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

            try {
                client = new OkHttpClient();
                response = apiCallDetailsIdea.GET(client, path, account_id, idea_id);
                Log.e("##GET_IDEAS#####: ", response);
                Gson gson = new Gson();

                modelIdea = gson.fromJson(response, ModelIdea.class);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details_my_idea, menu);
        return true;
    }
}

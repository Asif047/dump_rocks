package com.cbc_app_poc.rokomari.rokomarians.HappyWall;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DeleteHappyPost.DeleteHappyPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.ApiCallMyHappyPost;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.UpdateHappyPost.UpdateHappyPostActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelHappySeeAll;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;

import okhttp3.OkHttpClient;

public class DetailsMyHappyPostActivity extends AppCompatActivity {


    private SweetAlertDialog pDialog;
    private String BASE_URL ="http://192.168.11.231:9090/";
    private String path, response;
    private OkHttpClient client;
    private ModelHappySeeAll modelHappySeeAll;
    private ApiCallMyHappyPost apiCallMyHappyPost;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = "";
    private int post_id = 0;
    private TextView tvname, tvDetails;

    private DeleteHappyPostRequest deleteHappyPostRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_my_happy_post);

        final Intent intent = getIntent();
        post_id = intent.getIntExtra("happy_post_id", 0);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        apiCallMyHappyPost = new ApiCallMyHappyPost();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        tvname = findViewById(R.id.textview_my_happy_post_name);
        tvDetails = findViewById(R.id.textview_my_happy_post_details);

        deleteHappyPostRequest = new DeleteHappyPostRequest(this);

        try{
            path = BASE_URL + "happy-post/get-post";
            if(!myNetworkCheck.isConnected(this)){
                showAlert.showWarningNetDetailsHappyPostActivity();
            }
            else {
                new GetDataFromServer().execute();
            }

        } catch (Exception e){

        }

    }

    public void menuUpdateHappyPost(MenuItem item) {

        Intent intent = new Intent(this, UpdateHappyPostActivity.class);
        intent.putExtra("post_id", post_id);
        startActivity(intent);
    }

    public void menuDeleteHappyPost(MenuItem item) {

        deleteHappyPostRequest.deleteData(BASE_URL, account_id, post_id);
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

            if(pDialog.isShowing()){
                pDialog.dismiss();
            }

            tvname.setText(modelHappySeeAll.getName());
            tvDetails.setText(modelHappySeeAll.getDetails());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.details_happy_post_menu, menu);
        return true;
    }
}

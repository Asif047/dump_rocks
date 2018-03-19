package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelRecreationMyrecord;
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

public class MyRecordActivity extends AppCompatActivity {

    private TextView tvCategory, tvdetails, tvName;
    private String account_id = "", name;

    private SweetAlertDialog pDialog;
    private static final String BASE_URL = "http://192.168.11.231:9090/";
    private String path, response;
    private ModelRecreationMyrecord modelRecreationMyrecords;
    private ApiCallRecreationMyRecord apiCallRecreationMyRecord;
    private PartcipateDeleteRequest partcipateDeleteRequest;
    private List<ModelRecreationMyrecord> data;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;
    private OkHttpClient client;

    private CardView cardMyRecord;
    private FloatingActionButton btnAddParticipate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_record);

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

        apiCallRecreationMyRecord = new ApiCallRecreationMyRecord();

        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        tvCategory = findViewById(R.id.textview_category_recreation_my_record);
        tvdetails = findViewById(R.id.textview_details_recreation_my_record);
        tvName = findViewById(R.id.textview_name_recreation_my_record);
        cardMyRecord = findViewById(R.id.card_my_record);
        btnAddParticipate = findViewById(R.id.button_add_participate);

        partcipateDeleteRequest = new PartcipateDeleteRequest(this);

        tvName.setText(name);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        if(!myNetworkCheck.isConnected(MyRecordActivity.this)){
            showAlert.showWarningNetMyRecordActivity();
        } else {
             try{
                path = BASE_URL + "participation/my-record";
                new GetDataFromServer().execute();
            } catch (Exception e){

            }
        }


        cardMyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!tvCategory.getText().toString().equals("")){
                    Intent intent = new Intent(MyRecordActivity.this, EditRecreationHourActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MyRecordActivity.this, "Click Add to Participate",Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnAddParticipate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MyRecordActivity.this, ParticipateNowActivity.class);
                startActivity(intent);
            }
        });

    }

    public void updateParticipate(MenuItem item) {

        Intent intent = new Intent(MyRecordActivity.this, EditRecreationHourActivity.class);
        startActivity(intent);

    }

    public void DeleteParticipate(MenuItem item) {
        partcipateDeleteRequest.deleteData(BASE_URL, account_id);

    }


    private class GetDataFromServer extends AsyncTask< Void, Void, Void> {

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

            try{
                tvCategory.setText(modelRecreationMyrecords.getSelectedCategories());
                tvdetails.setText(modelRecreationMyrecords.getDetails());

                if(tvCategory.getText().toString().equals("")){
                    btnAddParticipate.setVisibility(View.VISIBLE);
                }

            } catch (Exception e){

            }


        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
             client = new OkHttpClient();
             response = apiCallRecreationMyRecord.GET(client, path, account_id);
             Log.e("###GET_MY_RECORD:", response);

             Gson gson = new Gson();
                Type type = new TypeToken<Collection<ModelRecreationMyrecord>>(){

                }.getType();

                modelRecreationMyrecords= gson.fromJson( response, ModelRecreationMyrecord.class);
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_record_menu, menu);
        return true;
    }

}

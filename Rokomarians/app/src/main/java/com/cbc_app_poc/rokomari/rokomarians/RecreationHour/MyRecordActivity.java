package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
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

    private TextView tvCategory, tvdetails;
    private String account_id = "";

    private SweetAlertDialog pDialog;
    private static final String BASE_URL = "http://192.168.11.231:9090/";
    private String path, response;
    private ModelRecreationMyrecord modelRecreationMyrecords;
    private ApiCallRecreationMyRecord apiCallRecreationMyRecord;
    private List<ModelRecreationMyrecord> data;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_record);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        apiCallRecreationMyRecord = new ApiCallRecreationMyRecord();
        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        tvCategory = findViewById(R.id.textview_category_recreation_my_record);
        tvdetails = findViewById(R.id.textview_details_recreation_my_record);


        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        if(!myNetworkCheck.isConnected(MyRecordActivity.this)){
            showAlert.showWarningNetMyRecordActivity();
        } else {
            try{
                path = BASE_URL + "recreation/my-record";
                new GetDataFromServer().execute();
            } catch (Exception e){

            }
        }

    }


    private class GetDataFromServer extends AsyncTask< Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            try{
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#aaaaaa"));
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

            tvCategory.setText(modelRecreationMyrecords.getSelectedCategories());
            tvdetails.setText(modelRecreationMyrecords.getDetails());

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


}

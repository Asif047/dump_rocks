package com.cbc_app_poc.rokomari.rokomarians.GoodWork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.ShowNominatedList.ApiCallNominatedWork;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.ShowNominatedList.RecyclerAdapterNominated;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelNominated;
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

public class GoodWorkActivity extends AppCompatActivity {

    private FloatingActionButton btnAddGoodWork;
    private SweetAlertDialog pDialog;
    private static final String BASE_URL = "http://192.168.11.231:9090/";
    private String path;
    private OkHttpClient client;
    private String response;
    private ModelNominated[] modelNominateds;
    private ApiCallNominatedWork apiCallNominatedWork;
    private List<ModelNominated> data;
    private RecyclerView recyclerView;
    private RecyclerAdapterNominated recyclerAdapterNominated;
    private RecyclerView.LayoutManager layoutManager;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_work);


        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        recyclerView = findViewById(R.id.recyclerview_good_work);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiCallNominatedWork = new ApiCallNominatedWork();
        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        btnAddGoodWork = findViewById(R.id.button_add_good_work);
        btnAddGoodWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodWorkActivity.this, NominateActivity.class);
                startActivity(intent);
            }
        });


        try{
            path = BASE_URL + "nomination/";

            if(!myNetworkCheck.isConnected(this)){
                showAlert.showWarningNetGoodWorkActivity();
            } else {
                new GetDataFromServer().execute();
            }

        } catch (Exception e){

        }

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

            if(pDialog.isShowing()) {
                pDialog.dismiss();
            }

            recyclerAdapterNominated = new RecyclerAdapterNominated(GoodWorkActivity.this, data, account_id);
            recyclerView.setAdapter(recyclerAdapterNominated);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response = apiCallNominatedWork.GET(client, path, account_id);
                Log.e("##SHOW_NOMINATEDS: ", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<ModelNominated>>() {

                }.getType();

                Collection<ModelNominated> enums = gson.fromJson(response, type);
                modelNominateds = enums.toArray(new ModelNominated[enums.size()]);

                if(data.isEmpty()){
                    for(int i = 0; i<enums.size(); i++){
                        data.add(modelNominateds[i]);
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }
    }


}

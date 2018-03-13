package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelEvent;
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

public class ParticipateNowActivity extends AppCompatActivity {

    private EditText etDescription;
    private TextView tvCategoryList;
    private Button btnParticipate, btnSelectCategory;

    private ParticipatePostRequest participatePostRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private Integer user_id;
    private static final String BASE_URL= "http://192.168.11.231:9090/";

    private String[] listItems;
    private boolean[] checkedItems;
    private ArrayList<Integer> mUserItems = new ArrayList<>();

    private SweetAlertDialog pDialog;
    private String account_id="", path, response;
    private OkHttpClient client;
    private ModelEvent[] modelEvents;
    private ApiCallEvents apiCallEvents;
    private List<ModelEvent> data;

    int id[];
    String item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_now);

//        listItems = new String[myCategory.length];
//        for(int i=0; i< myCategory.length; i++){
//            listItems[i] = myCategory[i];
//        }

        //getting category starts

        apiCallEvents = new ApiCallEvents();
        data = new ArrayList<>();



        //getting category ends

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        Integer restoredUser = prefs.getInt("user_id", 0);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredUser != null) {
            user_id = prefs.getInt("user_id", 0);
        }

        //getting account id ends


        try{
            path = BASE_URL + "recreation/events";
            new GetDataFromServer().execute();


        }catch (Exception e){

        }


        //getting account id starts
//        SharedPreferences prefs2 = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
//        String restoredAccount = prefs2.getString("account_id", null);


        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        //Toast.makeText(ParticipateNowActivity.this,""+account_id,Toast.LENGTH_LONG).show();

        participatePostRequest = new ParticipatePostRequest(this);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        etDescription = findViewById(R.id.edittext_description_participate);
        tvCategoryList = findViewById(R.id.textview_category_list);
        btnSelectCategory = findViewById(R.id.button_select_category);
        btnParticipate = findViewById(R.id.button_recreation_participate);





        //select category work starts

        //listItems =myCategory;

//        Toast.makeText(ParticipateNowActivity.this, ""+listItems[0], Toast.LENGTH_LONG).show();


        btnSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ParticipateNowActivity.this);
                mBuilder.setTitle("The Available categories are-");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {

                        if(isChecked){
                            if(!mUserItems.contains(position)){
                                mUserItems.add(position);
                            }
                        } else if(mUserItems.contains(position)){
                            mUserItems.remove(position-1);
                        }

                    }
                });


                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        item_id = "";
                        String item="";
                        id= new int[mUserItems.size()];

                        for ( int i = 0; i< mUserItems.size(); i++){
                            item = item + listItems[mUserItems.get(i)];
                            id[i] = modelEvents[mUserItems.get(i)].getId();
                            item_id = item_id + id[i];
                            if(i != mUserItems.size()-1){
                                item_id = item_id +",";
                                item = item+",";
                            }
                        }

                        tvCategoryList.setText(item);

                    }
                });


                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });


                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        for( int i=0; i< checkedItems.length; i++){
                            checkedItems[i] = false;
                            mUserItems.clear();
                            tvCategoryList.setText("");
                        }

                    }
                });


                AlertDialog mDialog = mBuilder.create();
                mDialog.show();


            }
        });


        //select category work ends


        btnParticipate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myNetworkCheck.isConnected(ParticipateNowActivity.this)){
                    showAlert.showWarningNetParticipateActivity();
                } else {

                    Toast.makeText(ParticipateNowActivity.this, ""+ item_id, Toast.LENGTH_LONG).show();
                    participatePostRequest.postData(BASE_URL,
                            ""+etDescription.getText().toString(),
                            item_id, account_id );

                }
            }
        });
    }


    private class GetDataFromServer extends AsyncTask< Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try{
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#aaaaaa"));
                pDialog.setCancelable(false);
                pDialog.setTitleText("Loading");
//                pDialog.show();
            } catch (Exception e){

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if(pDialog.isShowing()){
                pDialog.dismiss();
            }

            Log.e("###AAA:", listItems[2]);

//            Toast.makeText(ParticipateNowActivity.this, ""+modelEvents.getId(), Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                client = new OkHttpClient();
                response = apiCallEvents.GET(client, path,account_id);
                Log.e("###GET EVENT: ", response);
                Gson gson = new Gson();

                Type type = new TypeToken<Collection<ModelEvent>>(){

                }.getType();

                Collection<ModelEvent> enums = gson.fromJson(response, type);
                modelEvents = enums.toArray(new ModelEvent[enums.size()]);

                if(data.isEmpty()){
                    for( int i=0; i<enums.size(); i++){
                        data.add(modelEvents[i]);
                    }

                    Log.e("###AAAZZZ:", ""+enums.size());
                    listItems = new String[enums.size()];
                    for(int i= 0; i< enums.size(); i++){
                        listItems[i] = modelEvents[i].getEvent();
                    }

                    checkedItems = new boolean[listItems.length];


                }

            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }
    }

}

package com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.ApiCallSeeAll;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsSeeAll.DetailsSeeAllActivity;
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

import static android.content.Context.MODE_PRIVATE;

public class WhatNewFragment extends Fragment {

    private FloatingActionButton btnAddHappyPost;

    private SweetAlertDialog pDialog;
    private String BASE_URL = " http://192.168.11.231:9090/";
    private String path, response;
    private OkHttpClient client;
    private ModelHappySeeAll[] modelHappySeeAlls;
    private ApiCallSeeAll apiCallSeeAll;
    private List<ModelHappySeeAll> data;

    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = "";
    private TextView tvName1, tvName2, tvName3, tvName4, tvName5;
    private TextView tvPost1, tvPost2, tvPost3, tvPost4, tvPost5;

    private LinearLayout linearSticker1, linearSticker2, linearSticker3, linearSticker4, linearSticker5;
    private int id = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_what_new, container, false);

        tvName1 = view.findViewById(R.id.textview_name1_wall);
        tvName2 = view.findViewById(R.id.textview_name2_wall);
        tvName3 = view.findViewById(R.id.textview_name3_wall);
        tvName4 = view.findViewById(R.id.textview_name4_wall);
        tvName5 = view.findViewById(R.id.textview_name5_wall);

        tvPost1 = view.findViewById(R.id.textview_happy_post1_wall);
        tvPost2 = view.findViewById(R.id.textview_happy_post2_wall);
        tvPost3 = view.findViewById(R.id.textview_happy_post3_wall);
        tvPost4 = view.findViewById(R.id.textview_happy_post4_wall);
        tvPost5 = view.findViewById(R.id.textview_happy_post5_wall);

        linearSticker1 = view.findViewById(R.id.linear_sticker1);
        linearSticker2 = view.findViewById(R.id.linear_sticker2);
        linearSticker3 = view.findViewById(R.id.linear_sticker3);
        linearSticker4 = view.findViewById(R.id.linear_sticker4);
        linearSticker5 = view.findViewById(R.id.linear_sticker5);

        //getting account id starts
        SharedPreferences prefs = getContext().getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        apiCallSeeAll = new ApiCallSeeAll();
        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(getContext());

        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);

        if (!myNetworkCheck.isConnected(getContext())) {
            showAlert.showWarningNetHappySeeAllActivity();
        } else {
            try {
                path = BASE_URL + "happy-post/";
                new WhatNewFragment.GetDataFromServer().execute();
            } catch (Exception e) {

            }
        }


        linearSticker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = data.get(0).getId();

                Intent intent = new Intent(getContext(), DetailsSeeAllActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", "" + id);
                getContext().startActivity(intent);
            }
        });

        linearSticker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = data.get(1).getId();

                Intent intent = new Intent(getContext(), DetailsSeeAllActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", "" + id);
                getContext().startActivity(intent);
            }
        });


        linearSticker3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = data.get(2).getId();

                Intent intent = new Intent(getContext(), DetailsSeeAllActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", "" + id);
                getContext().startActivity(intent);
            }
        });

        linearSticker4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = data.get(3).getId();

                Intent intent = new Intent(getContext(), DetailsSeeAllActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", "" + id);
                getContext().startActivity(intent);
            }
        });


        linearSticker5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = data.get(4).getId();

                Intent intent = new Intent(getContext(), DetailsSeeAllActivity.class);
                intent.putExtra("happy_post_id", id);
                Log.e("###POST_ID: ", "" + id);
                getContext().startActivity(intent);
            }
        });


        return view;
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

            if (data.size() >= 1) {
                tvName1.setText(data.get(0).getName());
                tvPost1.setText(data.get(0).getDetails());
            }
            if (data.size() >= 2) {
                tvName1.setText(data.get(0).getName());
                tvPost1.setText(data.get(0).getDetails());
                tvName2.setText(data.get(1).getName());
                tvPost2.setText(data.get(1).getDetails());
            }
            if (data.size() >= 3) {
                tvName1.setText(data.get(0).getName());
                tvPost1.setText(data.get(0).getDetails());
                tvName2.setText(data.get(1).getName());
                tvPost2.setText(data.get(1).getDetails());
                tvName3.setText(data.get(2).getName());
                tvPost3.setText(data.get(2).getDetails());
            }
            if (data.size() >= 4) {
                tvName1.setText(data.get(0).getName());
                tvPost1.setText(data.get(0).getDetails());
                tvName2.setText(data.get(1).getName());
                tvPost2.setText(data.get(1).getDetails());
                tvName3.setText(data.get(2).getName());
                tvPost3.setText(data.get(2).getDetails());
                tvName4.setText(data.get(3).getName());
                tvPost4.setText(data.get(3).getDetails());
            }

            if (data.size() > 4) {
                tvName1.setText(data.get(0).getName());
                tvPost1.setText(data.get(0).getDetails());
                tvName2.setText(data.get(1).getName());
                tvPost2.setText(data.get(1).getDetails());
                tvName3.setText(data.get(2).getName());
                tvPost3.setText(data.get(2).getDetails());
                tvName4.setText(data.get(3).getName());
                tvPost4.setText(data.get(3).getDetails());
                tvName5.setText(data.get(4).getName());
                tvPost5.setText(data.get(4).getDetails());
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                client = new OkHttpClient();
                response = apiCallSeeAll.GET(client, path, account_id);
                Log.e("#SEE_ALL_HAPPY_POST:", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<ModelHappySeeAll>>() {

                }.getType();

                Collection<ModelHappySeeAll> enums = gson.fromJson(response, type);
                modelHappySeeAlls = enums.toArray(new ModelHappySeeAll[enums.size()]);

                if (data.isEmpty() && enums.size() >= 5) {
                    for (int i = 0; i < 5; i++) {
                        data.add(modelHappySeeAlls[i]);
                    }
                } else {
                    for (int i = 0; i < enums.size(); i++) {
                        data.add(modelHappySeeAlls[i]);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


}

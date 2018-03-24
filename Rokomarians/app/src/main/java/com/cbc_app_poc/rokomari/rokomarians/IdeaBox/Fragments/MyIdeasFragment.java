package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Adapter.RecyclerAdapterIdeas;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Adapter.RecyclerAdapterMyIdeas;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.ApiCalls.ApiCallidea;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelIdea;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;


public class MyIdeasFragment extends Fragment {

    private SweetAlertDialog pDialog;
    private String BASE_URL = "http://192.168.11.231:9090/";
    private String path, response;
    private OkHttpClient client;
    private ModelIdea[] modelIdeas;
    private ApiCallidea apiCallidea;
    private List<ModelIdea> data;
    private RecyclerView recyclerView;
    private RecyclerAdapterMyIdeas recyclerAdapterMyIdeas;
    private RecyclerView.LayoutManager layoutManager;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_ideas, container, false);

        //getting account id starts
        SharedPreferences prefs = getActivity().getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        //getting account id ends


        apiCallidea = new ApiCallidea();
        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(getContext());

        pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        recyclerView = view.findViewById(R.id.recyclerview_my_idea);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        try{
            path = BASE_URL + "idea-box/my-ideas";
            if(!myNetworkCheck.isConnected(getContext())) {
                showAlert.showWarningNetIdeaBoxActivity();
            } else {
                new MyIdeasFragment.GetDataFromServer().execute();
            }

        } catch (Exception e){

        }

        return view;

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
            recyclerAdapterMyIdeas = new RecyclerAdapterMyIdeas(getContext(), data);
            recyclerView.setAdapter(recyclerAdapterMyIdeas);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response = apiCallidea.GET(client, path, account_id);
                Log.e("##GET_MY_IDEAS: ", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<ModelIdea>>() {

                }.getType();
                Collection<ModelIdea> enums = gson.fromJson(response, type);
                modelIdeas = enums.toArray(new ModelIdea[enums.size()]);

                if(data.isEmpty()) {
                    for (int i = 0; i<enums.size(); i++) {
                        data.add(modelIdeas[i]);
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }
    }

}

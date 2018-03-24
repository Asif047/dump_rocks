package com.cbc_app_poc.rokomari.rokomarians.GoodWork;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GettingNominationList.ApiCallNomination;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GettingNominationList.RecyclerAdapterNominationList;
import com.cbc_app_poc.rokomari.rokomarians.Model.User;
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

public class NominateActivity extends AppCompatActivity {

    private SweetAlertDialog pDialog;
    private String BASE_URL = "http://192.168.11.231:9090/";
    private String path;
    private OkHttpClient client;
    private String response;
    private User[] users;
    private ApiCallNomination apiCallNomination;
    private List<User> data;
    private RecyclerView recyclerView;
    private RecyclerAdapterNominationList recyclerAdapterNominationList;
    private RecyclerView.LayoutManager layoutManager;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String account_id = "";
    private SearchView searchViewNomination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nominate);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        //search work starts
        searchViewNomination = findViewById(R.id.search_nomination);

        searchViewNomination.setQueryHint("Search ...");
        searchViewNomination.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        searchViewNomination.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchViewNomination.isIconified()) {
                    searchViewNomination.setIconified(true);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<User> filtermodelist = filter(data, newText);
                recyclerAdapterNominationList.setfilter(filtermodelist);
                return true;
            }
        });

        //search work ends

        searchViewNomination = findViewById(R.id.search_nomination);

        apiCallNomination = new ApiCallNomination();
        data = new ArrayList<>();
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        recyclerView = findViewById(R.id.recyclerview_nomination);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        if (!myNetworkCheck.isConnected(this)) {
            showAlert.showWarningNetNominationActivity();
        } else {
            try {
                path = BASE_URL + "users/";
                new GetDataFromServer().execute();
            } catch (Exception e) {

            }
        }


    }


    private List<User> filter(List<User> pl, String query) {
        query = query.toLowerCase();
        final List<User> filteredModeList = new ArrayList<>();
        for (User model : pl) {
            final String text = model.getFirstName().toLowerCase();
            if (text.startsWith(query)) {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
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

            recyclerAdapterNominationList = new RecyclerAdapterNominationList(NominateActivity.this, data);
            recyclerView.setAdapter(recyclerAdapterNominationList);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                client = new OkHttpClient();
                response = apiCallNomination.GET(client, path, account_id);
                Log.e("##GET_NOMINATION_LIST: ", response);
                Gson gson = new Gson();
                Type type = new TypeToken<Collection<User>>() {

                }.getType();

                Collection<User> enums = gson.fromJson(response, type);
                users = enums.toArray(new User[enums.size()]);

                if (data.isEmpty()) {
                    for (int i = 0; i < enums.size(); i++) {
                        data.add(users[i]);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }


    //back button operation starts
    @Override
    public void onBackPressed() {
        finish();
    }
    //back button operation ends

}

package com.cbc_app_poc.rokomari.rokomarians.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelProfile;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFirstname, tvEmail, tvPhone, tvAddress, tvHomeTown, tvEducation, tvHobbies,
            tvTeam, tvDesignation, tvWork, tvJoiningDate;
    private ImageView ivProfile;
    private FloatingActionButton btnEditProfile;

    private String account_id = "", email = "";

    private String first_name = "", last_name = "", image = "", email_profile = "", phone = "", address = "",
            hometown = "", education = "", hobbies = "", team = "", designation = "",
            work = "", joining_date = "";
    private int id;

    private static final String BASE_URL = "http://192.168.11.231:9090/";
    private String path, response;
    private OkHttpClient client;
    private ModelProfile modelProfiles;
    private ApiCallProfile apiCallProfile;
    private List<ModelProfile> data;

    private SweetAlertDialog pDialog;
    private ShowAlert showAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        apiCallProfile = new ApiCallProfile();
        data = new ArrayList<>();
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        showAlert = new ShowAlert(this);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);
        String restoredEmail = prefs.getString("email", null);
        String restoredImage = prefs.getString("user_image", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        if (restoredEmail != null) {
            email = prefs.getString("email", "No email defined");
        }

        if (restoredImage != null) {
            image = prefs.getString("user_image", "No image defined");
        }
        //getting account id ends


        try{
            path = BASE_URL+"users/profile";
            new GetDataFromServer().execute();
        }  catch (Exception e){

        }



        tvFirstname = findViewById(R.id.textview_name_profile);
        tvEmail = findViewById(R.id.textview_email_profile);
        tvPhone = findViewById(R.id.textview_phone_profile);
        tvAddress = findViewById(R.id.textview_address_profile);
        tvHomeTown = findViewById(R.id.textview_hometown_profile);
        tvEducation = findViewById(R.id.textview_education_profile);
        tvHobbies = findViewById(R.id.textview_hobbies_profile);
        tvTeam = findViewById(R.id.textview_team_profile);
        tvDesignation = findViewById(R.id.textview_designation_profile);
        tvWork = findViewById(R.id.textview_work_responsibility_profile);
        tvJoiningDate = findViewById(R.id.textview_joining_date_profile);

        ivProfile = findViewById(R.id.imageview_profile);

        btnEditProfile = findViewById(R.id.button_edit_profile);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProfileActivity.this, EditProfileActivity.class);

                intent1.putExtra("id", id);
                intent1.putExtra("first_name", first_name);
                intent1.putExtra("last_name", last_name);
                intent1.putExtra("image", image);
                intent1.putExtra("email_profile", email_profile);
                intent1.putExtra("phone", phone);
                intent1.putExtra("address", address);
                intent1.putExtra("hometown", hometown);
                intent1.putExtra("education", education);
                intent1.putExtra("hobbies", hobbies);
                intent1.putExtra("team", team);
                intent1.putExtra("designation", designation);
                intent1.putExtra("work", work);
                intent1.putExtra("joining_date", joining_date);
                startActivity(intent1);
            }
        });


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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(pDialog.isShowing()){
                pDialog.dismiss();
            }

            id = modelProfiles.getId();
            first_name = modelProfiles.getFirstName();
            last_name = modelProfiles.getLastName();
            image = modelProfiles.getImage().getImageUrl();
            email_profile = modelProfiles.getEmail();
            phone = modelProfiles.getPhone();
            address = modelProfiles.getUserPersonalInfo().getAddress();
            hometown = modelProfiles.getUserPersonalInfo().getHomeTown();
            education = modelProfiles.getUserPersonalInfo().getEducation();
            hobbies = modelProfiles.getUserPersonalInfo().getHobbies();
            team = modelProfiles.getUserOfficeInfo().getTeam();
            designation = modelProfiles.getUserOfficeInfo().getDesignation();
            work = modelProfiles.getUserOfficeInfo().getWork();
            joining_date = modelProfiles.getUserOfficeInfo().getJoiningDate();
            Toast.makeText(ProfileActivity.this, "" + id, Toast.LENGTH_LONG).show();


            tvFirstname.setText(first_name);
            tvEmail.setText(email_profile);
            tvPhone.setText(phone);
            tvAddress.setText(address);
            tvHomeTown.setText(hometown);
            tvEducation.setText(education);
            tvHobbies.setText(hobbies);
            tvTeam.setText(team);
            tvDesignation.setText(designation);
            tvWork.setText(work);
            tvJoiningDate.setText(joining_date);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                client = new OkHttpClient();
                response = apiCallProfile.GET(client, path, account_id);
                Log.e("###GET_PROFILE:", response);
                Gson gson = new Gson();

                modelProfiles = gson.fromJson(response, ModelProfile.class);
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;

        }
    }


}

package com.cbc_app_poc.rokomari.rokomarians.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etAddress, etHometown, etEducation,
                    etHobbies, etTeam, etDesignation, etWork;
    private FloatingActionButton btnSaveProfile;
    private String first_name="", last_name="",image="", phone="", address="", hometown="",
            education="", hobbies="", team="", designation="", work="", joining_date="";

    private Role.User user;
    private Role.UserPersonalInfo userPersonalInfo;
    private Role.UserOfficeInfo userOfficeInfo;

    private EditProfilePostRequest editProfilePostRequest;
    private String account_id="";
    private int id;

    private static final String BASE_URL="http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfilePostRequest = new EditProfilePostRequest(this);

        //getting account id starts
        SharedPreferences prefs=getSharedPreferences("Profile_PREF",MODE_PRIVATE);
        String restoredAccount=prefs.getString("account_id",null);

        if(restoredAccount!=null)
        {
            account_id=prefs.getString("account_id","No account defined");
        }

        etFirstName = findViewById(R.id.edittext_first_name_edit_profile);
        etLastName = findViewById(R.id.edittext_last_name_edit_profile);
        etPhone = findViewById(R.id.edittext_phone_edit_profile);
        etAddress = findViewById(R.id.edittext_address_edit_profile);
        etHometown = findViewById(R.id.edittext_hometown_edit_profile);
        etEducation = findViewById(R.id.edittext_education_edit_profile);
        etHobbies = findViewById(R.id.edittext_hobbies_edit_profile);
        etTeam = findViewById(R.id.edittext_team_edit_profile);
        etDesignation = findViewById(R.id.edittext_designation_edit_profile);
        etWork = findViewById(R.id.edittext_work_responsibility_edit_profile);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        image = intent.getStringExtra("image");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");
        hometown = intent.getStringExtra("hometown");
        education = intent.getStringExtra("education");
        hobbies = intent.getStringExtra("hobbies");
        team = intent.getStringExtra("team");
        designation = intent.getStringExtra("designation");
        work = intent.getStringExtra("work");
        joining_date = intent.getStringExtra("joining_date");

        etFirstName.setText(first_name);
        etLastName.setText(last_name);
        etPhone.setText(phone);
        etAddress.setText(address);
        etHometown.setText(hometown);
        etEducation.setText(education);
        etHobbies.setText(hobbies);
        etTeam.setText(team);
        etDesignation.setText(designation);
        etWork.setText(work);

        btnSaveProfile = findViewById(R.id.button_save_profile);



        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userPersonalInfo = new Role.UserPersonalInfo(""+etHometown.getText().toString(),
                        ""+etEducation.getText().toString(),""+etHobbies.getText().toString(),
                        ""+etAddress.getText().toString());

                userOfficeInfo = new Role.UserOfficeInfo(""+etTeam.getText().toString(),
                        ""+etDesignation.getText().toString(), ""+etWork.getText().toString(),
                        ""+joining_date);

                user = new Role.User(""+etFirstName.getText().toString(), ""+etLastName.getText().toString(),
                        ""+etPhone.getText().toString(),""+image,"ACTIVE",userPersonalInfo,userOfficeInfo);


                editProfilePostRequest.postData(BASE_URL, user, account_id, id);

            }
        });



    }
}

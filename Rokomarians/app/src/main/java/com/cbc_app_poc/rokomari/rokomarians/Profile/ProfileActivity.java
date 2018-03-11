package com.cbc_app_poc.rokomari.rokomarians.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFirstname, tvEmail, tvPhone, tvAddress, tvHomeTown, tvEducation, tvHobbies,
            tvTeam, tvDesignation, tvWork, tvJoiningDate;
    private ImageView ivProfile;
    private FloatingActionButton btnEditProfile;

    private String account_id = "", email = "";
    private ProfileMyPostRequest profileMyPostRequest;
    private Role role;

    private String first_name = "", last_name = "", image = "", email_profile = "", phone = "", address = "",
            hometown = "", education = "", hobbies = "", team = "", designation = "",
            work = "", joining_date = "";
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        role = new Role();
        profileMyPostRequest = new ProfileMyPostRequest(this);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);
        String restoredEmail = prefs.getString("email", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        if (restoredEmail != null) {
            email = prefs.getString("email", "No email defined");
        }
        //getting account id ends

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        image = intent.getStringExtra("image");
        email_profile = intent.getStringExtra("email_profile");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");
        hometown = intent.getStringExtra("hometown");
        education = intent.getStringExtra("education");
        hobbies = intent.getStringExtra("hobbies");
        team = intent.getStringExtra("team");
        designation = intent.getStringExtra("designation");
        work = intent.getStringExtra("work");
        joining_date = intent.getStringExtra("joining_date");
        Toast.makeText(ProfileActivity.this, "" + id, Toast.LENGTH_LONG).show();


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


}

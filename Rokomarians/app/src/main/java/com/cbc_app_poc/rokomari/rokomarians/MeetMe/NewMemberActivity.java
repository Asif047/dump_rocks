package com.cbc_app_poc.rokomari.rokomarians.MeetMe;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.MeetMe.Fragment.ContactInfoFragment;
import com.cbc_app_poc.rokomari.rokomarians.MeetMe.Fragment.SayHiFragment;
import com.cbc_app_poc.rokomari.rokomarians.MeetMe.Fragment.TeamFragment;
import com.cbc_app_poc.rokomari.rokomarians.MeetMe.Fragment.WhoFragment;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class NewMemberActivity extends AppCompatActivity {

    private TextView mTextMessage;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction =fragmentManager.beginTransaction();



            switch (item.getItemId()) {
                case R.id.navigation_who:

                    transaction.replace(R.id.content_new_member, new WhoFragment()).commit();
                    return true;
                case R.id.navigation_contact_info:

                    transaction.replace(R.id.content_new_member, new ContactInfoFragment()).commit();
                    return true;
                case R.id.navigation_team:

                    transaction.replace(R.id.content_new_member, new TeamFragment()).commit();
                    return true;
                case R.id.navigation_say_hi:
                    transaction.replace(R.id.content_new_member, new SayHiFragment()).commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_member);

        BottomNavigationView navigationView;
        navigationView = findViewById(R.id.navigation);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        final Intent intent = getIntent();
        int cardFlag = 0;
        cardFlag = intent.getIntExtra("cardFlag",0);
        if(cardFlag == 0)
            transaction.replace(R.id.content_new_member, new WhoFragment()).commit();
        else if(cardFlag == 1)
            transaction.replace(R.id.content_new_member, new ContactInfoFragment()).commit();
        else if(cardFlag == 2)
            transaction.replace(R.id.content_new_member, new TeamFragment()).commit();
        else if(cardFlag == 3)
            transaction.replace(R.id.content_new_member, new SayHiFragment()).commit();

        navigationView.getMenu().getItem(cardFlag).setChecked(false);
        mOnNavigationItemSelectedListener.onNavigationItemSelected(navigationView.getMenu().getItem(cardFlag));

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

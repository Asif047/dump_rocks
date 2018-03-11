package com.cbc_app_poc.rokomari.rokomarians;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GoodWorkActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyWallActivity;
import com.cbc_app_poc.rokomari.rokomarians.How_r_u_feeling.FeelingsActivity;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.IdeaBoxActivity;
import com.cbc_app_poc.rokomari.rokomarians.Journey.SplashJourney;
import com.cbc_app_poc.rokomari.rokomarians.MeetMe.MeetMeActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelHome;
import com.cbc_app_poc.rokomari.rokomarians.Profile.ProfileMyPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.RecreationHour.ParticipateNowActivity;
import com.cbc_app_poc.rokomari.rokomarians.RecreationHour.RecreationListActivity;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;
import com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo.WhatActivity;
import com.google.gson.Gson;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.OkHttpClient;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;

    private TextView tvJourney, tvIdea, tvGoodWork, tvRecreation, tvWhat, tvHappyWall, tvMeetMe,
            tvFeeling, tvNotification;

    private CardView cardJourney, cardIdea, cardMeetMe, cardGoodWork, cardRecreation, cardHappyWall, cardWhat, cardFeelings;

    private String account_id = "", email = "", email_profile = "", first_name = "", last_name = "", image = "";

    private ProfileMyPostRequest profileMyPostRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    SweetAlertDialog pDialog;
    private String BASE_URL = "http://192.168.11.231:9090/", path, response;
    OkHttpClient client;
    private ModelHome modelHomes;
    private ApiCallHome apiCallHome;
    NavigationView nvDrawer;


    private static final String BASE_URL_PROFILE ="http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        apiCallHome = new ApiCallHome();
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);

        profileMyPostRequest = new ProfileMyPostRequest(this);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

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
        //Toast.makeText(HomeActivity.this, email, Toast.LENGTH_LONG).show();


        cardJourney = findViewById(R.id.card_journey);
        cardIdea = findViewById(R.id.card_idea);
        cardMeetMe = findViewById(R.id.card_meet_me);
        cardGoodWork = findViewById(R.id.card_good_work);
        cardRecreation = findViewById(R.id.card_recreation);
        cardHappyWall = findViewById(R.id.card_happy_wall);
        cardWhat = findViewById(R.id.card_what);
        cardFeelings = findViewById(R.id.card_feeling);

        tvJourney = findViewById(R.id.textview_journey);
        tvIdea = findViewById(R.id.textview_idea);
        tvGoodWork = findViewById(R.id.textview_good_work);
        tvRecreation = findViewById(R.id.textview_recreation);
        tvWhat = findViewById(R.id.textview_what_should_i_do);
        tvHappyWall = findViewById(R.id.textview_happy_wall);
        tvMeetMe = findViewById(R.id.textview_meet_me);
        tvFeeling = findViewById(R.id.textview_feeling);
        tvNotification = findViewById(R.id.textview_notification);

        Typeface tf = Typeface.createFromAsset(getAssets(), "font_amaranth/Amaranth-Bold.ttf");
        tvJourney.setTypeface(tf);
        tvIdea.setTypeface(tf);
        tvGoodWork.setTypeface(tf);
        tvRecreation.setTypeface(tf);
        tvWhat.setTypeface(tf);
        tvHappyWall.setTypeface(tf);
        tvMeetMe.setTypeface(tf);
        tvFeeling.setTypeface(tf);

        getSupportActionBar().hide();

        tvNotification.setText("* This is a news bulletin. This is a long news bulletin. Hope you all are well.............");
        tvNotification.setSelected(true);
        //get notification ends

        dl = (DrawerLayout) findViewById(R.id.dl);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Setting toolbar as the ActionBar with setSupportActionBar() call


        toolbar.setTitle("Home Page");
        toolbar.inflateMenu(R.menu.logout_menu);


        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);

        nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawerContent(nvDrawer);


        cardJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SplashJourney.class);
                startActivity(intent);
            }
        });

        cardIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, IdeaBoxActivity.class);
                startActivity(intent);
            }
        });

        cardMeetMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MeetMeActivity.class);
                startActivity(intent);
            }
        });

        cardGoodWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GoodWorkActivity.class);
                startActivity(intent);
            }
        });

        cardRecreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ParticipateNowActivity.class);
                startActivity(intent);
            }
        });

        cardHappyWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HappyWallActivity.class);
                startActivity(intent);
            }
        });

        cardWhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WhatActivity.class);
                startActivity(intent);
            }
        });

        cardFeelings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FeelingsActivity.class);
                startActivity(intent);
            }
        });


        try {
            path = BASE_URL + "logged-in-user/";
            if(account_id != null){
                new GetDataFromServer().execute();
            }

        } catch (Exception e) {

        }

    }


    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.home:
//                Toast.makeText(RememberMeActivity.this,"hello",Toast.LENGTH_LONG).show();
//                fragmentClass = Network.class;
                break;
            case R.id.profile:
                if (!myNetworkCheck.isConnected(HomeActivity.this)) {
                    showAlert.showWarningNetHomeActivity();
                } else {
                    profileMyPostRequest.postData(BASE_URL_PROFILE, email, account_id);
                }
                break;
            case R.id.journey:
//                Intent intent=new Intent(HomeActivity.this,MakeNoteActivity.class);
//                startActivity(intent);
                break;
            case R.id.idea:
                Intent intent2 = new Intent(HomeActivity.this, IdeaBoxActivity.class);
                startActivity(intent2);
                break;
            case R.id.good_work:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;

            case R.id.recreation:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;

            case R.id.what:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;

            case R.id.happy_wall:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;

            case R.id.meet_me:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;

            case R.id.feeling:
//                Intent intent3=new Intent(HomeActivity.this,ShowWorkUpdateActivity.class);
//                startActivity(intent3);
                break;


            default:
                // fragmentClass = Network.class;
        }
        try {
//            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();
        menuItem.setChecked(true);
        //setTitle(menuItem.getTitle());
        dl.closeDrawers();


    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }


    public void logoutClick(MenuItem item) {
        account_id = null;
        SharedPreferences.Editor editor = getSharedPreferences("Profile_PREF", MODE_PRIVATE).edit();
        editor.putString("account_id", account_id);
        editor.apply();

        Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);

        return true;
    }


    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try{
                //new starts
                View hView = nvDrawer.getHeaderView(0);
                TextView nav_user_name = (TextView) hView.findViewById(R.id.textview_name_header);
                TextView nav_user_email = (TextView) hView.findViewById(R.id.textview_email_header);
                ImageView nav_user_image = (CircleImageView) hView.findViewById(R.id.image_header);

                nav_user_name.setText(modelHomes.getFirstName());
                nav_user_email.setText(modelHomes.getEmail());
                Picasso.with(HomeActivity.this).load(modelHomes.getImagePath());

                SharedPreferences.Editor editor = getSharedPreferences("Profile_PREF", MODE_PRIVATE).edit();
                editor.putInt("user_id", modelHomes.getId());
                editor.apply();
                //new ends

                if(modelHomes.getId() == null){
                    Intent intent = new Intent(HomeActivity.this, LogInActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(HomeActivity.this, modelHomes.getFirstName(), Toast.LENGTH_LONG).show();
            } catch (Exception e){

            }


        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                client = new OkHttpClient();
                response = apiCallHome.GET(client, path, email, account_id);
                Log.e("##HOME_JSON: ", response);

                Gson gson = new Gson();
                modelHomes = gson.fromJson(response, ModelHome.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }

    //back button operation starts
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    //back button operation ends


}

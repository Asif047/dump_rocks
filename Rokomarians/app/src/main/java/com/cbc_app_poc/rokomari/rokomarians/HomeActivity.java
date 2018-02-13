package com.cbc_app_poc.rokomari.rokomarians;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GoodWorkActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyWallActivity;
import com.cbc_app_poc.rokomari.rokomarians.How_r_u_feeling.FeelingsActivity;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.IdeaBoxActivity;
import com.cbc_app_poc.rokomari.rokomarians.Journey.SplashJourney;
import com.cbc_app_poc.rokomari.rokomarians.MeetMe.MeetMeActivity;
import com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo.WhatActivity;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout dl ;
    private ActionBarDrawerToggle toggle ;
    private Toolbar toolbar;

    private TextView tvJourney,tvIdea,tvGoodWork,tvRecreation,tvWhat,tvHappyWall,tvMeetMe,
                        tvFeeling,tvNotification;

    private CardView cardJourney,cardIdea, cardMeetMe,cardGoodWork,cardHappyWall,cardWhat,cardFeelings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardJourney = findViewById(R.id.card_journey);
        cardIdea = findViewById(R.id.card_idea);
        cardMeetMe = findViewById(R.id.card_meet_me);
        cardGoodWork = findViewById(R.id.card_good_work);
        cardHappyWall = findViewById(R.id.card_happy_wall);
        cardWhat = findViewById(R.id.card_what);
        cardFeelings = findViewById(R.id.card_feeling);

        tvJourney=findViewById(R.id.textview_journey);
        tvIdea=findViewById(R.id.textview_idea);
        tvGoodWork=findViewById(R.id.textview_good_work);
        tvRecreation=findViewById(R.id.textview_recreation);
        tvWhat=findViewById(R.id.textview_what_should_i_do);
        tvHappyWall=findViewById(R.id.textview_happy_wall);
        tvMeetMe=findViewById(R.id.textview_meet_me);
        tvFeeling=findViewById(R.id.textview_feeling);
        tvNotification =findViewById(R.id.textview_notification);

        Typeface tf=Typeface.createFromAsset(getAssets(), "font_amaranth/Amaranth-Bold.ttf");
        tvJourney.setTypeface(tf);
        tvIdea.setTypeface(tf);
        tvGoodWork.setTypeface(tf);
        tvRecreation.setTypeface(tf);
        tvWhat.setTypeface(tf);
        tvHappyWall.setTypeface(tf);
        tvMeetMe.setTypeface(tf);
        tvFeeling.setTypeface(tf);

        getSupportActionBar().hide();


        //get notification starts
//        Animation animationToLeft = new TranslateAnimation(500, -600, 0, 0);
//        animationToLeft.setDuration(12000);
//        animationToLeft.setRepeatMode(Animation.RESTART);
//        animationToLeft.setRepeatCount(Animation.INFINITE);
//
//        tvNotification.setAnimation(animationToLeft);
        tvNotification.setText("* This is a news bulletin. This is a long news bulletin. Hope you all are well.............");
        tvNotification.setSelected(true);
        //get notification ends

       dl = (DrawerLayout) findViewById(R.id.dl);
       toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Setting toolbar as the ActionBar with setSupportActionBar() call
       // setSupportActionBar(toolbar);

        toolbar.setTitle("Home Page");


        toggle = new ActionBarDrawerToggle(this,dl,toolbar,R.string.open,R.string.close);
        dl.addDrawerListener(toggle);

        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawerContent(nvDrawer);


        //new starts
        View hView =  nvDrawer.getHeaderView(0);
        TextView nav_user_name = (TextView)hView.findViewById(R.id.textview_name_header);
        TextView nav_user_email = (TextView)hView.findViewById(R.id.textview_email_header);

        nav_user_name.setText("Asif");
        nav_user_email.setText("asif@rokomari.com");
        //new ends

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
                Intent intent=new Intent(HomeActivity.this,IdeaBoxActivity.class);
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

    }



    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.home:
//                Toast.makeText(RememberMeActivity.this,"hello",Toast.LENGTH_LONG).show();
//                fragmentClass = Network.class;
                break;
            case R.id.profile:
//                Intent intent1=new Intent(HomeActivity.this,AllNotesActivity.class);
//                startActivity(intent1);
                break;
            case R.id.journey:
//                Intent intent=new Intent(HomeActivity.this,MakeNoteActivity.class);
//                startActivity(intent);
                break;
            case R.id.idea:
                Intent intent2=new Intent(HomeActivity.this,IdeaBoxActivity.class);
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
        }
        catch (Exception e) {
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


}

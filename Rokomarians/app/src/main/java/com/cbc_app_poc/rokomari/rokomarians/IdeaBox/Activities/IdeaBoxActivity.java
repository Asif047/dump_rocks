package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.HappyWall.SectionPageadapter;
import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Fragments.IdeasFragment;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Fragments.MyIdeasFragment;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Fragments.WriteIdeaFragment;
import com.cbc_app_poc.rokomari.rokomarians.Profile.ProfileActivity;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class IdeaBoxActivity extends AppCompatActivity {

    private static final String TAG="IdeaBoxActivity";
    private SectionPageadapter mSectionPageadapter;
    private ViewPager mViewPager;

    private Toolbar toolbar;


    private DrawerLayout dl;
    private ActionBarDrawerToggle toggle;
    NavigationView nvDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_box);



       getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbar_idea_box);
        toolbar.setTitle("Idea Box");



        Log.d(TAG,"on create:starting.");

        mSectionPageadapter=new SectionPageadapter(getSupportFragmentManager());

        mViewPager= (ViewPager) findViewById(R.id.container_idea);
        setupViewPager(mViewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs_idea);
        tabLayout.setupWithViewPager(mViewPager);


        // navigation drawer work starts

        dl = (DrawerLayout) findViewById(R.id.dl);
        nvDrawer = (NavigationView) findViewById(R.id.nv);

        toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.open, R.string.close);
        dl.addDrawerListener(toggle);

        toggle.syncState();

        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawerContent(nvDrawer);

        // navigation drawer work ends



    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionPageadapter sectionPageadapter=new SectionPageadapter(getSupportFragmentManager());
        sectionPageadapter.addFragment(new IdeasFragment(),"Ideas");
        sectionPageadapter.addFragment(new WriteIdeaFragment(),"Write Idea");
        sectionPageadapter.addFragment(new MyIdeasFragment(),"My Ideas");

        viewPager.setAdapter(sectionPageadapter);

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

                    Intent intent = new Intent(IdeaBoxActivity.this, ProfileActivity.class);
                    startActivity(intent);

                break;
            case R.id.journey:
//                Intent intent=new Intent(HomeActivity.this,MakeNoteActivity.class);
//                startActivity(intent);
                break;
            case R.id.idea:
                Intent intent2 = new Intent(IdeaBoxActivity.this, IdeaBoxActivity.class);
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





}

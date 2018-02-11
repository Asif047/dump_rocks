package com.cbc_app_poc.rokomari.rokomarians.IdeaBox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class IdeaBoxActivity extends AppCompatActivity {

    private FloatingActionButton btnAddIdea;

    private DrawerLayout dl ;
    private ActionBarDrawerToggle toggle ;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_box);

        dl = (DrawerLayout) findViewById(R.id.dl);
        toolbar = (Toolbar) findViewById(R.id.toolbar_idea);

        // Setting toolbar as the ActionBar with setSupportActionBar() call



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

        btnAddIdea = findViewById(R.id.button_add_idea);
        btnAddIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(IdeaBoxActivity.this, PostIdeaActivity.class);
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
                Intent intent2=new Intent(IdeaBoxActivity.this,IdeaBoxActivity.class);
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

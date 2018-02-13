package com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.HappyPostFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.SeeAllFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.WhatNewFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.SectionPageadapter;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo.Fragments.AddProblemsFragment;
import com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo.Fragments.ProblemsFragment;
import com.cbc_app_poc.rokomari.rokomarians.WhatShouldIDo.Fragments.SuggestionsFragment;

public class WhatActivity extends AppCompatActivity {

    private static final String TAG="WhatActivity";
    private SectionPageadapter mSectionPageadapter;
    private ViewPager mViewPager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what);


        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbar_what);
        toolbar.setTitle("Happy Wall");

        Log.d(TAG,"on create:starting.");

        mSectionPageadapter=new SectionPageadapter(getSupportFragmentManager());

        mViewPager= (ViewPager) findViewById(R.id.container_what);
        setupViewPager(mViewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs_what);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionPageadapterWhat sectionPageadapter=new SectionPageadapterWhat(getSupportFragmentManager());
        sectionPageadapter.addFragment(new ProblemsFragment(),"Problems");
        sectionPageadapter.addFragment(new SuggestionsFragment(),"Suggestions");
        sectionPageadapter.addFragment(new AddProblemsFragment(),"Add problems");

        viewPager.setAdapter(sectionPageadapter);

    }

}

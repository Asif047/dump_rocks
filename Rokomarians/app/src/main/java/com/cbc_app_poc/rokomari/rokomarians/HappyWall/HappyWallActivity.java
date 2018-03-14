package com.cbc_app_poc.rokomari.rokomarians.HappyWall;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.HappyPostFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.MyHappyPostFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.SeeAllFragment;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments.WhatNewFragment;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class HappyWallActivity extends AppCompatActivity {

    private static final String TAG="HappyWallActivity";
    private SectionPageadapter mSectionPageadapter;
    private ViewPager mViewPager;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_wall);

        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbar_happy_wall);
        toolbar.setTitle("Happy Wall");

        Log.d(TAG,"on create:starting.");

        mSectionPageadapter=new SectionPageadapter(getSupportFragmentManager());

        mViewPager= (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionPageadapter sectionPageadapter=new SectionPageadapter(getSupportFragmentManager());
        sectionPageadapter.addFragment(new WhatNewFragment(),"What's New");
        sectionPageadapter.addFragment(new SeeAllFragment(),"See All");
        sectionPageadapter.addFragment(new HappyPostFragment(),"Write New");
        sectionPageadapter.addFragment(new MyHappyPostFragment(), "My Posts");

        viewPager.setAdapter(sectionPageadapter);

    }


}

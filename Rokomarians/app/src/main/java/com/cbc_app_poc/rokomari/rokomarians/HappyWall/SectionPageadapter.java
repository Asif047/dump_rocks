package com.cbc_app_poc.rokomari.rokomarians.HappyWall;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPageadapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList=new ArrayList<>();
    private final List<String>mfragmentTitleList=new ArrayList<>();


    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }


    public SectionPageadapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

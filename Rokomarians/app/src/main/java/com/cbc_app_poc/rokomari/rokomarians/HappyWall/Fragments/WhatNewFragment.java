package com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.cbc_app_poc.rokomari.rokomarians.MeetMe.Fragment.WhoFragment;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class WhatNewFragment extends Fragment {

    private FloatingActionButton btnAddHappyPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_what_new, container, false);

        return  view;
    }


}

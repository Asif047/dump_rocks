package com.cbc_app_poc.rokomari.rokomarians.AlertBox;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GoodWorkActivity;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.NominateActivity;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.PostNominateActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsSeeAll.DetailsSeeAllActivity;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyWallActivity;
import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities.IdeaBoxActivity;
import com.cbc_app_poc.rokomari.rokomarians.LogInActivity;
import com.cbc_app_poc.rokomari.rokomarians.Profile.ProfileActivity;
import com.cbc_app_poc.rokomari.rokomarians.RecreationHour.MyRecordActivity;
import com.cbc_app_poc.rokomari.rokomarians.RecreationHour.ParticipateNowActivity;
import com.cbc_app_poc.rokomari.rokomarians.Register.RegisterActivity;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class ShowAlert {
    private SweetAlertDialog sweetAlertDialog;
    private Context context;

    public ShowAlert(Context context) {
        this.context = context;
    }

    public void showWarningNetRegisterActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, RegisterActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetLogInActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, LogInActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }




    public void showWarningNetHomeActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetParticipateActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, ParticipateNowActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetMyRecordActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, MyRecordActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetProfileActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }


    public void showWarningNetHappyPostActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }


    public void showWarningNetHappySeeAllActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, ProfileActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetDetailsHappyPostActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, HappyWallActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }



    public void showWarningNetNominationActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, NominateActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }

    public void showWarningNetPostNominationActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, PostNominateActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }


    public void showWarningNetGoodWorkActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, GoodWorkActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }


    public void showWarningNetIdeaBoxActivity()
    {
        sweetAlertDialog=new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText("No Internet Connection")
                .setContentText("Please turn on the internet connection and then press OK")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ((Activity)context).finish();
                        Intent intent = new Intent(context, IdeaBoxActivity.class);
                        context.startActivity(intent);
                    }
                })
                .show();
    }




}

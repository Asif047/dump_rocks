package com.cbc_app_poc.rokomari.rokomarians.AlertBox;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.LogInActivity;
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



}

package com.cbc_app_poc.rokomari.rokomarians.HappyWall.LikeHappyPost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.HappyWall.DetailsSeeAll.DetailsSeeAllActivity;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

public class LikeResponseActivity extends AppCompatActivity {

    private String response_status;
    private SweetAlertDialog sDialogSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_response);

        sDialogSuccess = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        Intent intent = getIntent();

        response_status = intent.getStringExtra("response_like_happy");

        Toast.makeText(LikeResponseActivity.this, response_status, Toast.LENGTH_LONG).show();

        if( response_status.trim().equals("200")){
            showSuccess();
        }
    }

    void showSuccess(){
        sDialogSuccess.setCancelable(false);
        sDialogSuccess.setTitleText("Liked")
                .setContentText("You Liked it!!!")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                       // finish();

                        Intent intent = new Intent(LikeResponseActivity.this, DetailsSeeAllActivity.class);
                        startActivity(intent);
                    }
                }).show();
    }

}

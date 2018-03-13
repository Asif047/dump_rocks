package com.cbc_app_poc.rokomari.rokomarians;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.Register.RegisterActivity;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

public class LogInActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private TextView tvLoginPage;
    private Button btnSignIn;

    private LogInMyPostRequest logInMyPostRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private static final String BASE_URL = "http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInMyPostRequest = new LogInMyPostRequest(this);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        etEmail = findViewById(R.id.edittext_email_login);
        etPassword = findViewById(R.id.edittext_password_login);
        tvLoginPage = findViewById(R.id.textview_login_page);
        btnSignIn = findViewById(R.id.button_sign_in);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Please Enter an Email");
                }
                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Please Enter a Password");
                }

                if(!etEmail.getText().toString().isEmpty() &&
                        !etPassword.getText().toString().isEmpty()){

                    if(!myNetworkCheck.isConnected(LogInActivity.this)){
                        showAlert.showWarningNetLogInActivity();
                    } else{
                        logInMyPostRequest.postData(BASE_URL, etEmail.getText().toString(), etPassword.getText().toString());
                    }

                }
            }
        });

        tvLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }



    //back button operation starts
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    //back button operation ends


}

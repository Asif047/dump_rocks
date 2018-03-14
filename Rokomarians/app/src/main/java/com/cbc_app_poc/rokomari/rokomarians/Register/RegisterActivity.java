package com.cbc_app_poc.rokomari.rokomarians.Register;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.LogInActivity;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword, etPhone;
    private TextView tvregisterPage;
    private Button btnSignUp;
    private RegisterMyPostRequest registerMyPostRequest;
    private Role.User user;
    String emailPattern;

    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;
    private final static String BASE_URL = "http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerMyPostRequest = new RegisterMyPostRequest(this);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        etFirstName = findViewById(R.id.edittext_first_name_register);
        etLastName = findViewById(R.id.edittext_last_name_register);
        etEmail = findViewById(R.id.edittext_email_register);
        etPassword = findViewById(R.id.edittext_password_register);
        etPhone = findViewById(R.id.edittext_phone_register);

        tvregisterPage = findViewById(R.id.textview_register_page);

        btnSignUp = findViewById(R.id.button_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etFirstName.getText().toString().isEmpty()){
                    etFirstName.setError("Please enter the First Name");
                }
                if(etLastName.getText().toString().isEmpty()){
                    etLastName.setError("Please enter the Last Name");
                }
                if(etEmail.getText().toString().isEmpty()){
                    etEmail.setError("Please enter the Email");
                }
                if(etPassword.getText().toString().isEmpty()){
                    etPassword.setError("Please enter the Password");
                }
                if(etPhone.getText().toString().isEmpty()){
                    etPhone.setError("Please enter the Phone number");
                }

                emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (etEmail.getText().toString().matches(emailPattern)){

                    if(!etFirstName.getText().toString().isEmpty() &&
                            !etLastName.getText().toString().isEmpty() &&
                            !etPassword.getText().toString().isEmpty() &&
                            !etPhone.getText().toString().isEmpty()) {

                        user = new Role.User(""+etFirstName.getText().toString(),""+
                                etLastName.getText().toString(),""+
                                etEmail.getText().toString(), ""+
                                etPassword.getText().toString(), ""+
                                etPhone.getText().toString(),
                                null,
                                "ACTIVE"
                        );

                        if(!myNetworkCheck.isConnected(RegisterActivity.this)){
                            showAlert.showWarningNetRegisterActivity();
                        } else {
                            registerMyPostRequest.postData(BASE_URL,user);
                        }

                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "Invalid Email Address",Toast.LENGTH_SHORT).show();
                }

            }
        });


        tvregisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
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

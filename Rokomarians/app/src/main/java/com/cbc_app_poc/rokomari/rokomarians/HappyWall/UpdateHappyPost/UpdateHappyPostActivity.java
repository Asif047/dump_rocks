package com.cbc_app_poc.rokomari.rokomarians.HappyWall.UpdateHappyPost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

public class UpdateHappyPostActivity extends AppCompatActivity {

    private TextView tvName;
    private ImageView ivUser;
    private EditText etDetails;
    private Button btnUpdate;

    private String account_id = "";
    private String BASE_URL = "http://192.168.11.231:9090/";
    private UpdateHappyPutRequest updateHappyPutRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private int post_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_happy_post);

        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        final Intent intent = getIntent();
        post_id = intent.getIntExtra("post_id", post_id);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        //getting account id ends

        updateHappyPutRequest = new UpdateHappyPutRequest(this);

        tvName = findViewById(R.id.textview_name_update_happy_post);
        ivUser = findViewById(R.id.imageview_update_happy_post);
        etDetails = findViewById(R.id.edittext_update_happy_post);
        btnUpdate = findViewById(R.id.button_update_happy_post);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHappyPutRequest.putData(BASE_URL, account_id, post_id, etDetails.getText().toString());
            }
        });

    }
}

package com.cbc_app_poc.rokomari.rokomarians.GoodWork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.GoodWork.GettingNominationList.PostNominationRequest;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

public class PostNominateActivity extends AppCompatActivity {

    private TextView tvNominatedName;
    private EditText etNominatedReason;
    private Button btnAddNomination;
    private int nominated_id = 0;
    private String nominated_name = "", nominated_image = "";
    private String account_id = "";

    private PostNominationRequest postNominationRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private String BASE_URL = "http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_nominate);

        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);
        postNominationRequest = new PostNominationRequest(this);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        postNominationRequest = new PostNominationRequest(this);

        tvNominatedName = findViewById(R.id.textview_nominated_name);
        etNominatedReason = findViewById(R.id.edittext_reason_nomination);
        btnAddNomination = findViewById(R.id.button_add_nomination);

        final Intent intent = getIntent();
        nominated_id = intent.getIntExtra("nominated_id", nominated_id);
        nominated_name = intent.getStringExtra("nominated_name");
        nominated_image = intent.getStringExtra("nominated_image");

        tvNominatedName.setText(nominated_name);
        //Toast.makeText(PostNominateActivity.this, nominated_name+"", Toast.LENGTH_LONG).show();

        btnAddNomination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myNetworkCheck.isConnected(PostNominateActivity.this)){
                    showAlert.showWarningNetPostNominationActivity();
                } else {
                    postNominationRequest.postData(BASE_URL, nominated_id, etNominatedReason.getText().toString(),
                            account_id);
                }
            }
        });



    }
}

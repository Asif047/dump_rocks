package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.Model.ModelParticipate;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

public class ParticipateNowActivity extends AppCompatActivity {

    private EditText etDescription;
    private Button btnParticipate;

    private ModelParticipate modelParticipate;
    private ModelParticipate.RecreationHour recreationHour;
    private ParticipatePostRequest participatePostRequest;
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    private Integer user_id;
    private String account_id;
    private static final String BASE_URL= "http://192.168.11.231:9090/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participate_now);

        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        Integer restoredUser = prefs.getInt("user_id", 0);

        if (restoredUser != null) {
            user_id = prefs.getInt("user_id", 0);
        }

        //getting account id ends

        //getting account id starts
        SharedPreferences prefs2 = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs2.getString("account_id", null);


        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        Toast.makeText(ParticipateNowActivity.this,""+account_id,Toast.LENGTH_LONG).show();

        participatePostRequest = new ParticipatePostRequest(this);
        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(this);

        etDescription = findViewById(R.id.edittext_description_participate);
        btnParticipate = findViewById(R.id.button_recreation_participate);


        btnParticipate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myNetworkCheck.isConnected(ParticipateNowActivity.this)){
                    showAlert.showWarningNetParticipateActivity();
                } else {
                    recreationHour = new ModelParticipate.RecreationHour(etDescription.getText().toString(),
                                                        "SONG");
                    modelParticipate = new ModelParticipate(recreationHour,user_id);
                    participatePostRequest.postData(BASE_URL, modelParticipate, account_id );

                }
            }
        });
    }
}

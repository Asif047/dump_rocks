package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests.UpdateIdeaRequest;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class EditMyIdeaActivity extends AppCompatActivity {

    private int idea_id;
    private EditText etTitle, etIdea;
    private Button btnUpdate;

    private UpdateIdeaRequest updateIdeaRequest;
    private static final String URL_UPDATE_IDEA = "http://192.168.11.231:9090/idea-box/";

    private String account_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_idea);


        //getting account id starts
        SharedPreferences prefs = getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }
        //getting account id ends

        etTitle = findViewById(R.id.edittext_title_idea_edit);
        etIdea = findViewById(R.id.edittext_idea_edit);
        btnUpdate = findViewById(R.id.button_add_idea);

        updateIdeaRequest = new UpdateIdeaRequest(this);

        final Intent intent = getIntent();
        idea_id = intent.getIntExtra("idea_id", 0);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateIdeaRequest.putData(URL_UPDATE_IDEA, idea_id, etTitle.getText().toString(),
                        etIdea.getText().toString(), account_id);
            }
        });


    }
}

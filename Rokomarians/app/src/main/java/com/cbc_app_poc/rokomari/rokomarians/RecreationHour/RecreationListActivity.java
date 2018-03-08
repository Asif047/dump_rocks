package com.cbc_app_poc.rokomari.rokomarians.RecreationHour;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cbc_app_poc.rokomari.rokomarians.R;

public class RecreationListActivity extends AppCompatActivity {

    private FloatingActionButton btnAddRecreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recreation_list);

        btnAddRecreation = findViewById(R.id.button_add_recreation);
        btnAddRecreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecreationListActivity.this, ParticipateCategoryActivity.class);
                startActivity(intent);
            }
        });

    }
}

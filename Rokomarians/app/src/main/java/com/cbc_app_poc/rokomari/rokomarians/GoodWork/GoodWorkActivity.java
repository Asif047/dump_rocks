package com.cbc_app_poc.rokomari.rokomarians.GoodWork;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cbc_app_poc.rokomari.rokomarians.R;

public class GoodWorkActivity extends AppCompatActivity {

    private FloatingActionButton btnAddGoodWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_work);

        btnAddGoodWork = findViewById(R.id.button_add_good_work);
        btnAddGoodWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoodWorkActivity.this, NominateActivity.class);
                startActivity(intent);
            }
        });
    }
}

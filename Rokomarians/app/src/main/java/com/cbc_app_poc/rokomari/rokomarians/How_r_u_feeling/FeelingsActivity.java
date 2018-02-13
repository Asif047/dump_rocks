package com.cbc_app_poc.rokomari.rokomarians.How_r_u_feeling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import com.cbc_app_poc.rokomari.rokomarians.HomeActivity;
import com.cbc_app_poc.rokomari.rokomarians.R;

public class FeelingsActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick;
    private Button btnPostFeeling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feelings);

        btnPostFeeling = findViewById(R.id.button_post_feelings);
        buttonClick = new AlphaAnimation(1F, 0.8F);

        btnPostFeeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClick);
                Intent intent = new Intent(FeelingsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

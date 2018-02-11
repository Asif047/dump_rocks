package com.cbc_app_poc.rokomari.rokomarians.Journey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.R;

public class SplashJourney extends AppCompatActivity {

    private ImageView ivcar;
    private TextView tvJourney;
    Animation fromLeftCar, fromLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_journey);

        ivcar = findViewById(R.id.image_splash_rokomari);
        tvJourney = findViewById(R.id.rokomari_journey);

        fromLeftCar = AnimationUtils.loadAnimation(this, R.anim.car_move_left_to_right);
        fromLeftText = AnimationUtils.loadAnimation(this, R.anim.text_move_left_to_right);

        ivcar.setAnimation(fromLeftCar);
        tvJourney.setAnimation(fromLeftText);




        //new starts
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);   // set the duration of splash screen
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

//                        Intent intent = new Intent(SplashScreenActivity.this,HomeActivity.class);
//                        startActivity(intent);
//                        finish();

                        Intent intent = new Intent(SplashJourney.this,JourneyActivity.class);
                        startActivity(intent);
                        finish();
                        // overridePendingTransition(R.anim.move_right_to_left_enter, R.anim.move_right_to_left_exit);


                }
            }
        };
        timer.start();

        //new ends

    }
}

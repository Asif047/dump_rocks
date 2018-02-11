package com.cbc_app_poc.rokomari.rokomarians.MeetMe;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.cbc_app_poc.rokomari.rokomarians.R;

public class MeetMeActivity extends AppCompatActivity {

    private TextView tvWho, tvContactInfo, tvTeam, tvSayHi;
    private CardView cardWho, cardContact, cardTeam, cardSayHi;
    private int cardFlag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_me);

        tvWho = findViewById(R.id.textview_who_meet_me);
        tvContactInfo = findViewById(R.id.textview_contact_meet_me);
        tvTeam = findViewById(R.id.textview_team_meet_me);
        tvSayHi = findViewById(R.id.textview_say_hi_meet_me);

        cardWho = findViewById(R.id.card_who);
        cardContact = findViewById(R.id.card_contact);
        cardTeam = findViewById(R.id.card_team);
        cardSayHi = findViewById(R.id.card_say_hi);

        Typeface tf = Typeface.createFromAsset(getAssets(), "font_amaranth/Amaranth-Bold.ttf");
        tvWho.setTypeface(tf); tvContactInfo.setTypeface(tf);
        tvTeam.setTypeface(tf); tvSayHi.setTypeface(tf);

        cardWho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFlag = 0;
                Intent intent = new Intent(MeetMeActivity.this, NewMemberActivity.class);
                intent.putExtra("cardFlag",cardFlag);
                startActivity(intent);
                overridePendingTransition(R.anim.move_bottom_to_up_enter,R.anim.move_bottom_to_up_exit);
            }
        });

        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFlag = 1;
                Intent intent = new Intent(MeetMeActivity.this, NewMemberActivity.class);
                intent.putExtra("cardFlag",cardFlag);
                startActivity(intent);
                overridePendingTransition(R.anim.move_right_to_left_enter,R.anim.move_right_to_left_exit);
            }
        });

        cardTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFlag = 2;
                Intent intent = new Intent(MeetMeActivity.this, NewMemberActivity.class);
                intent.putExtra("cardFlag",cardFlag);
                startActivity(intent);
                overridePendingTransition(R.anim.move_left_to_right_enter,R.anim.move_left_to_right_exit);
            }
        });

        cardSayHi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardFlag = 3;
                Intent intent = new Intent(MeetMeActivity.this, NewMemberActivity.class);
                intent.putExtra("cardFlag",cardFlag);
                startActivity(intent);
                overridePendingTransition(R.anim.move_up_to_bottom_enter, R.anim.move_up_to_bottom_exit);
            }
        });

    }
}

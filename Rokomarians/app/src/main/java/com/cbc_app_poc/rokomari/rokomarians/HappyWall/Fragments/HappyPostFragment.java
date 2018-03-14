package com.cbc_app_poc.rokomari.rokomarians.HappyWall.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.AlertBox.ShowAlert;
import com.cbc_app_poc.rokomari.rokomarians.HappyWall.HappyPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.R;
import com.cbc_app_poc.rokomari.rokomarians.Utils.MyNetworkCheck;

import java.util.zip.Inflater;

import static android.content.Context.MODE_PRIVATE;


public class HappyPostFragment extends Fragment {

    private EditText etHappyPost;
    private Button btnHappyPost;
    private TextView tvName;

    private HappyPostRequest happyPostRequest;
    private String url = "http://192.168.11.231:9090/happy-post/new-post";
    private String account_id = "", user_name="";
    private MyNetworkCheck myNetworkCheck;
    private ShowAlert showAlert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_happy_post, container, false);


        //getting account id starts
        SharedPreferences prefs = getContext().getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        String restoredName = prefs.getString("user_name", null);

        if (restoredName != null) {
            user_name = prefs.getString("user_name", "No account defined");
        }
        //getting account id ends

        myNetworkCheck = new MyNetworkCheck();
        showAlert = new ShowAlert(getContext());

        happyPostRequest = new HappyPostRequest(getContext());

        etHappyPost = view.findViewById(R.id.edittext_happy_post);
        btnHappyPost = view.findViewById(R.id.button_add_happy_post);
        tvName = view.findViewById(R.id.textview_name_happy_post);

        tvName.setText(user_name);

        btnHappyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!myNetworkCheck.isConnected(getContext())){
                    showAlert.showWarningNetHappyPostActivity();
                } else {
                    happyPostRequest.postData(etHappyPost.getText().toString(), url, account_id);
                }
            }
        });

        return view;

    }

}

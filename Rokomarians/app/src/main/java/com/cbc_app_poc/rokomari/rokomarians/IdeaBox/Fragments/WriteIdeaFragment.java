package com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Fragments;

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

import com.cbc_app_poc.rokomari.rokomarians.IdeaBox.Requests.IdeaPostRequest;
import com.cbc_app_poc.rokomari.rokomarians.R;

import static android.content.Context.MODE_PRIVATE;


public class WriteIdeaFragment extends Fragment {


    private EditText etTitle, etIdea;
    private IdeaPostRequest ideaPostRequest;
    private Button btnAddIdea;

    private String account_id = "";
    private static final String BASE_URL = "http://192.168.11.231:9090/idea-box/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_write_idea, container, false);


        //getting account id starts
        SharedPreferences prefs = getActivity().getSharedPreferences("Profile_PREF", MODE_PRIVATE);
        String restoredAccount = prefs.getString("account_id", null);

        if (restoredAccount != null) {
            account_id = prefs.getString("account_id", "No account defined");
        }

        //getting account id ends


        etTitle = view.findViewById(R.id.edittext_title_idea);
        etIdea = view.findViewById(R.id.edittext_idea);
        btnAddIdea = view.findViewById(R.id.button_add_idea);

        ideaPostRequest = new IdeaPostRequest(getContext());
        btnAddIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ideaPostRequest.postData(etTitle.getText().toString(), etIdea.getText().toString(),
                        account_id, BASE_URL);
            }
        });

        return view;
    }

}

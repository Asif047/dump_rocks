package com.cbc_app_poc.rokomari.rokomarians.Profile;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cbc_app_poc.rokomari.rokomarians.ImageUpload.APIUtils;
import com.cbc_app_poc.rokomari.rokomarians.ImageUpload.FileInfo;
import com.cbc_app_poc.rokomari.rokomarians.ImageUpload.FileService;
import com.cbc_app_poc.rokomari.rokomarians.Model.Role;
import com.cbc_app_poc.rokomari.rokomarians.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etPhone, etAddress, etHometown, etEducation,
                    etHobbies, etTeam, etDesignation, etWork, etJoiningDate;
    private TextView tvChangeImage, tvUploadImage;
    private FloatingActionButton btnSaveProfile;
    private String first_name="", last_name="",image="", phone="", address="", hometown="",
            education="", hobbies="", team="", designation="", work="", joining_date="";

    private Role.User user;
    private Role.UserPersonalInfo userPersonalInfo;
    private Role.UserOfficeInfo userOfficeInfo;

    private EditProfilePostRequest editProfilePostRequest;
    private String account_id="";
    private int id;

    private static final String BASE_URL="http://192.168.11.231:9090/";
    private FileService fileService;
    private String imagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editProfilePostRequest = new EditProfilePostRequest(this);

        //getting account id starts
        SharedPreferences prefs=getSharedPreferences("Profile_PREF",MODE_PRIVATE);
        String restoredAccount=prefs.getString("account_id",null);

        if(restoredAccount!=null)
        {
            account_id=prefs.getString("account_id","No account defined");
        }

        etFirstName = findViewById(R.id.edittext_first_name_edit_profile);
        etLastName = findViewById(R.id.edittext_last_name_edit_profile);
        etPhone = findViewById(R.id.edittext_phone_edit_profile);
        etAddress = findViewById(R.id.edittext_address_edit_profile);
        etHometown = findViewById(R.id.edittext_hometown_edit_profile);
        etEducation = findViewById(R.id.edittext_education_edit_profile);
        etHobbies = findViewById(R.id.edittext_hobbies_edit_profile);
        etTeam = findViewById(R.id.edittext_team_edit_profile);
        etDesignation = findViewById(R.id.edittext_designation_edit_profile);
        etWork = findViewById(R.id.edittext_work_responsibility_edit_profile);
        etJoiningDate = findViewById(R.id.edittext_joining_date_edit_profile);

        tvChangeImage = findViewById(R.id.textview_change_image);
        tvUploadImage = findViewById(R.id.textview_upload_image);

        final Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        image = intent.getStringExtra("image");
        phone = intent.getStringExtra("phone");
        address = intent.getStringExtra("address");
        hometown = intent.getStringExtra("hometown");
        education = intent.getStringExtra("education");
        hobbies = intent.getStringExtra("hobbies");
        team = intent.getStringExtra("team");
        designation = intent.getStringExtra("designation");
        work = intent.getStringExtra("work");
        joining_date = intent.getStringExtra("joining_date");

        etFirstName.setText(first_name);
        etLastName.setText(last_name);
        etPhone.setText(phone);
        etAddress.setText(address);
        etHometown.setText(hometown);
        etEducation.setText(education);
        etHobbies.setText(hobbies);
        etTeam.setText(team);
        etDesignation.setText(designation);
        etWork.setText(work);
        if(joining_date == null){
            etJoiningDate.setText("");
        } else {
            etJoiningDate.setText(joining_date);
        }


        btnSaveProfile = findViewById(R.id.button_save_profile);
        fileService = APIUtils.getFileService();



        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userPersonalInfo = new Role.UserPersonalInfo(""+etHometown.getText().toString(),
                        ""+etEducation.getText().toString(),""+etHobbies.getText().toString(),
                        ""+etAddress.getText().toString());

                userOfficeInfo = new Role.UserOfficeInfo(""+etTeam.getText().toString(),
                        ""+etDesignation.getText().toString(), ""+etWork.getText().toString(),
                        ""+etJoiningDate.getText().toString());

                user = new Role.User(""+etFirstName.getText().toString(), ""+etLastName.getText().toString(),
                        ""+etPhone.getText().toString(),""+image,"ACTIVE",userPersonalInfo,userOfficeInfo);


                editProfilePostRequest.postData(BASE_URL, user, account_id, id);

            }
        });


        tvChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                startActivityForResult(intent1, 0);

                tvUploadImage.setVisibility(View.VISIBLE);
            }
        });


        tvUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(imagePath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part
                        .createFormData("image", file.getName(), requestBody);
                Log.e("###Image_name:", file.getName());
                Call<FileInfo> call = fileService.upload(account_id ,body);
                call.enqueue(new Callback<FileInfo>() {
                    @Override
                    public void onResponse(Call<FileInfo> call, Response<FileInfo> response) {
                        Toast.makeText(EditProfileActivity.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<FileInfo> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            if(data == null){
                Toast.makeText(this, "Unable to choose image !", Toast.LENGTH_SHORT).show();
            }

            Uri imageUri = data.getData();
            imagePath = getRealPathFromUri(imageUri);
        }

    }


    private String getRealPathFromUri(Uri uri){
        String[] projection = { MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri,
                projection, null, null, null);

        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }


}

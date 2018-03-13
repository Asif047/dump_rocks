package com.cbc_app_poc.rokomari.rokomarians.ImageUpload;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

//    @Multipart
//    @Headers("Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhQGEuYSIsImV4cCI6MTUyMTM1MTI4N30.-tlqD6H1o9YYTfKmmL0ebrH3K8e_yi3m3o1DZ6IAGBm2OMjdRTorW4BzXczQLmRAnGImlEsmpZ1nFXKV3fBVtw")
//    @POST("image/upload-image")
//    Call<FileInfo> upload(@Part MultipartBody.Part file);

    @Multipart
    @POST("image/upload-image")
    Call<FileInfo> upload(@Header("Authorization") String authorization, @Part MultipartBody.Part file);

}

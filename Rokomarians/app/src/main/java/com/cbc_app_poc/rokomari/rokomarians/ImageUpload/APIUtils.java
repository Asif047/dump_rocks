package com.cbc_app_poc.rokomari.rokomarians.ImageUpload;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL= "http://192.168.11.231:9090/";
    public static FileService getFileService(){
        return RetrofitClient.getClient(API_URL).create(FileService.class);
    }

}
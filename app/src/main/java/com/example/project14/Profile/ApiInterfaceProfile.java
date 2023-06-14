package com.example.project14.Profile;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterfaceProfile {

    @GET("user/")
    Call<JsonObject> getUsers();

    @POST("login/")
    Call<JsonObject> postLogin(@Body HashMap<String, String> loginData);

    @GET("profile/")
    Call<JsonObject> getProfile();
}
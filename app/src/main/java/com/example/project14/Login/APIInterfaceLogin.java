package com.example.project14.Login;

import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterfaceLogin {

    @GET("user/")
    Call<JsonObject> getUsers();

    @POST("login/")
    Call<JsonObject> postLogin(@Body HashMap<String, String> loginData);
}
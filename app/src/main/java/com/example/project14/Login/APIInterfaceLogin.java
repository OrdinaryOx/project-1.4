package com.example.project14.Login;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterfaceLogin {

    @GET("user/")
    Call<JsonObject> getUsers();
}
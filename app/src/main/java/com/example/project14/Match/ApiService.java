package com.example.project14.Match;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("user/huurder")
    Call<JsonObject> getItems();

    @GET("user/verhuurder")
    Call<JsonObject> getVerhuurders();

    @POST("user/huurder/match")
    Call<JsonObject> getHuurderMatch();

    @POST("user/verhuurder/match")
    Call<JsonObject> getVerhuurderMatch();
}

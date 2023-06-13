package com.example.project14.Match;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET("user/huurder")
    Call<JsonObject> getItems();

@GET
Call<JsonObject> getFilterHuurder(@Url String url);

    @GET("user/verhuurder")
    Call<JsonObject> getVerhuurders();

}

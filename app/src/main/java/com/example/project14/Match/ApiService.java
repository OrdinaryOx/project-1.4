package com.example.project14.Match;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("user/huurder")
    Call<JsonObject> getItems();

}

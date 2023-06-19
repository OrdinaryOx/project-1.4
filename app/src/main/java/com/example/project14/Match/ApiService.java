package com.example.project14.Match;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;
import retrofit2.http.POST;

public interface ApiService {

    @GET("user/huurder")
    Call<JsonObject> getItems();

    @GET
    Call<JsonObject> getFilterHuurder(@Url String url);

    @GET
    Call<JsonObject> getFilterVerhuurder(@Url String url);

    @GET("user/verhuurder")
    Call<JsonObject> getVerhuurders();

    @GET("user/huurder/match")
    Call<JsonObject> getHuurderMatch();

    @GET("user/verhuurder/match")
    Call<JsonObject> getVerhuurderMatch();

    @PUT("profile/phone")
    Call<Void> updatePhoneNumberVisibility(@Body RequestBody body);


}

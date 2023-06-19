package com.example.project14.Profile;

import com.example.project14.Match.Match;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterfaceProfile {

    @GET("user/")
    Call<JsonObject> getUsers();

    @POST("login/")
    Call<JsonObject> postLogin(@Body HashMap<String, String> loginData);

    @GET("profile/")
    Call<JsonObject> getProfile();

    @PUT("{userID}/")
    Call<JsonObject> updateHuurder(@Path("userID") String userID, @Body RequestBody requestBody);

    @PUT("{userID}/")
    Call<JsonObject> updateVerhuurder(@Path("userID") String userID, @Body RequestBody requestBody);
}
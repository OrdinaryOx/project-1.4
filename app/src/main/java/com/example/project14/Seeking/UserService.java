package com.example.project14.Seeking;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/huurder")
    Call<ResponseBody> postHuurder(@Body JSONObject userPreferences);
}


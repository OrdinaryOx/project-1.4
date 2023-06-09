package com.example.project14.Match;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("user/huurder")
    Call<List<Match>> getItems();

}

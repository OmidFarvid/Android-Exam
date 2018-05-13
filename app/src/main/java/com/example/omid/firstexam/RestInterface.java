package com.example.omid.firstexam;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RestInterface {
    @GET("countries")
    Call<ResponseBody> GetCountries();
}

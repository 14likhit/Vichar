package com.likhit.vichar.data;

import com.likhit.vichar.data.model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/top-headlines")
    Call<News> getNews(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apikey);
}

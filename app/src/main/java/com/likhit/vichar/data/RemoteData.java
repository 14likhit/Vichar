package com.likhit.vichar.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.likhit.vichar.data.ApiClient;
import com.likhit.vichar.data.ApiService;
import com.likhit.vichar.data.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteData {

    public void getNews() {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        final Call<News> request = service.getNews("us", "2381cf8e88fa431c834cddbc53b4976f");
        request.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                Log.e("API", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }

}

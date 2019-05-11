package com.likhit.vichar.ui.home;

import android.support.annotation.NonNull;
import android.util.Log;

import com.likhit.vichar.base.BasePresenter;
import com.likhit.vichar.data.ApiClient;
import com.likhit.vichar.data.ApiService;
import com.likhit.vichar.data.model.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    public HomePresenter() {
    }

    @Override
    public void getNewsArticle(String category) {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        final Call<News> request = service.getNews("in", category.toLowerCase(), "2381cf8e88fa431c834cddbc53b4976f");
        request.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NonNull Call<News> call, @NonNull Response<News> response) {
                Log.e("API", String.valueOf(response.body()));
                if (getView() != null && response.body() != null) {
                    getView().onNewsReceived(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(@NonNull Call<News> call, @NonNull Throwable t) {
                Log.e("API", t.getMessage());
            }
        });
    }
}

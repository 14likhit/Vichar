package com.likhit.vichar.ui.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.R;
import com.likhit.vichar.data.ApiClient;
import com.likhit.vichar.data.ApiService;
import com.likhit.vichar.data.model.News;
import com.likhit.vichar.databinding.ActivitySplashBinding;
import com.likhit.vichar.ui.home.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        startTimer();
    }

    private void startTimer() {
        //30 Sec Timer.
        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                launchActivity();
            }
        }, 5000);
    }

    void launchActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        this.startActivity(intent);
    }

}

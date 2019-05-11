package com.likhit.vichar.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.likhit.vichar.R;
import com.likhit.vichar.utils.Utils;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void setupToolbar(@Nullable String title, boolean homeButtonEnable) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }

        if (!homeButtonEnable) {
            int inset = (int) Utils.dpToPx(this, 16);
            toolbar.setContentInsetsAbsolute(inset, inset);
        }

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(homeButtonEnable);
            actionBar.setDisplayShowHomeEnabled(homeButtonEnable);

//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onBackPressed();
//                }
//            });
        }
    }

    protected BaseFragment getCurrentFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() == null || !getCurrentFragment().onBackPressed()) {
            super.onBackPressed();
        }
    }


    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int messageResId) {

    }
}

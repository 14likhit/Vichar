package com.likhit.vichar.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.R;
import com.likhit.vichar.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setupToolbar("Account", true);
    }
}

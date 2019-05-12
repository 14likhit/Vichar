package com.likhit.vichar.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.likhit.vichar.base.BaseActivity;
import com.likhit.vichar.R;
import com.likhit.vichar.databinding.ActivityLoginBinding;
import com.likhit.vichar.sharedpreference.Preferences;
import com.likhit.vichar.ui.home.HomeActivity;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        setupToolbar("Account", true);

        initView();
    }

    private void initView() {
        binding.btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable username = binding.etUserName.getText();
                Editable loginId = binding.etLoginId.getText();
                Editable password = binding.etPassword.getText();
                if (username == null || username.length() == 0) {
                    binding.userNameInputLayout.setError("Please Enter Username");
                } else if (loginId == null || loginId.length() == 0) {
                    binding.loginIdInputLayout.setError("Please Enter LoginId");
                } else if (password == null || password.length() == 0) {
                    binding.passwordInputLayout.setError("Please Enter Password");
                } else {
                    Preferences.getInstance().setPrefUsername(username.toString());
                    Preferences.getInstance().setPrefUserid(loginId.toString());
                    Preferences.getInstance().setUserLoggedIn(true);
                    Toast.makeText(getBaseContext(), "Successfully LoggedIn", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), HomeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getBaseContext().startActivity(i);
                }
            }
        });
    }
}

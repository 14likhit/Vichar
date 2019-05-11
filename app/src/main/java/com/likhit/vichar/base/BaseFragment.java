package com.likhit.vichar.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int messageResId) {

    }
    public boolean onBackPressed() {
        return false;
    }
}

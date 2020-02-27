package com.batdemir.template.ui.activities.login;

import com.batdemir.template.R;
import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.base.activity.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginController> {

    @Override
    public void onCreated() {
        init(R.layout.activity_login, R.style.AppThemeActionBar, "Login", 16f, false, true);
    }

    @Override
    public void getObjectReferences() {
        //Not implemented
    }

    @Override
    public void loadData() {
        //Not implemented
    }

    @Override
    public void setListeners() {
        //Not implemented
    }
}
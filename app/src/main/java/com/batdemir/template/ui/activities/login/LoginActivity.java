package com.batdemir.template.ui.activities.login;

import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.base.activity.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginController> {

    @Override
    public void onCreated() {
        init(new ActivityBuilder().setFirstActivity(true));
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
        getBinding().editTextUserName.addTextChangedListener(getController().addTextChangeEvent(getBinding().inputLayoutUserName));
        getBinding().editTextUserPassword.addTextChangedListener(getController().addTextChangeEvent(getBinding().inputLayoutUserPassword));
        getBinding().editTextUserPassword.setOnEditorActionListener(getController().addOnEditorActionListener());
        getBinding().btnLogin.setOnClickListener(v -> getController().clickLogin());
        getBinding().txtCopyright.setOnClickListener(v -> getController().clickCopyright());
    }
}
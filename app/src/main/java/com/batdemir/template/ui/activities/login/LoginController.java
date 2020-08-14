package com.batdemir.template.ui.activities.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.batdemir.mylibrary.tools.ToolSharedPreferences;
import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.base.controller.BaseController;
import com.batdemir.utilities.MethodHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginController extends BaseController<ActivityLoginBinding> {

    public LoginController(ActivityLoginBinding binding) {
        super(binding);
    }

    void isUserRemembered() {
        String userGson = ToolSharedPreferences.getInstance(getBinding().getRoot().getContext()).getString("user");
        if (userGson.isEmpty())
            return;
    }

    void clickLogin() {
        if (checkInput()) {
            Snackbar.make(getBinding().getRoot().getRootView(), "Success", Snackbar.LENGTH_SHORT).show();
        } else {
            MethodHelper.getInstance().errorProcess(getBinding().cardView);
        }
    }

    void clickCopyright() {
        throw new NullPointerException();
    }

    //----------------LOGICAL----------------

    private boolean checkInput() {
        if (Objects.requireNonNull(getBinding().editTextUserName.getText()).toString().isEmpty()) {
            getBinding().inputLayoutUserName.setError("Please enter user name !");
            return false;
        }
        if (Objects.requireNonNull(getBinding().editTextUserPassword.getText()).toString().isEmpty()) {
            getBinding().inputLayoutUserPassword.setError("Please enter password !");
            return false;
        }
        return true;
    }

    //----------------EVENTS----------------

    public TextWatcher addTextChangeEvent(TextInputLayout textInputLayout) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Not implemented
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Not implemented
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (textInputLayout.getError() != null)
                    textInputLayout.setError(null);
            }
        };
    }

    public TextView.OnEditorActionListener addOnEditorActionListener() {
        return (v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                clickLogin();
                return true;
            }
            return false;
        };
    }
}
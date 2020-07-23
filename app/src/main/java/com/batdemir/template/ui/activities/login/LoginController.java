package com.batdemir.template.ui.activities.login;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.base.controller.BaseController;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginController extends BaseController<ActivityLoginBinding> {

    public LoginController(ActivityLoginBinding binding) {
        super(binding);
    }

    void clickLogin() {
        if (checkInput()) {
            Snackbar.make(getBinding().getRoot().getRootView(), "Success", Snackbar.LENGTH_SHORT).show();
        } else {
            ObjectAnimator rotate = ObjectAnimator.ofFloat(getBinding().cardView, "rotation", 0f, 1f, 0f, -1f, 0f);
            rotate.setRepeatCount(5);
            rotate.setDuration(25);
            rotate.start();
            Vibrator vibrator = (Vibrator) getBinding().getRoot().getContext().getSystemService(Context.VIBRATOR_SERVICE);
            Objects.requireNonNull(vibrator).vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    void clickCopyright() {
        throw new NullPointerException();
    }

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
}
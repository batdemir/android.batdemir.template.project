package com.batdemir.template.ui.activities.base.factory;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.viewbinding.ViewBinding;

import com.batdemir.template.databinding.ActivityLoginBinding;

public class BindingFactory {

    private static BindingFactory instance;

    private BindingFactory() {

    }

    public static synchronized BindingFactory getInstance() {
        if (instance == null)
            instance = new BindingFactory();
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <B extends ViewBinding> B getBinding(String strBinding, LayoutInflater inflater) {
        if (strBinding == null)
            throw new NullPointerException("Binding Not Found");

        if (strBinding.equalsIgnoreCase("Login")) {
            ActivityLoginBinding binding = ActivityLoginBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (B) binding;
        }

        throw new NullPointerException("Binding Not Found");
    }
}

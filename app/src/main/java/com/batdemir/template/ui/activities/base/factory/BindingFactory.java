package com.batdemir.template.ui.activities.base.factory;

import android.app.Activity;
import android.view.LayoutInflater;

import com.batdemir.template.databinding.ActivityLoginBinding;

@SuppressWarnings({"squid:S00119", "squid:S1121", "unchecked"})
public class BindingFactory {

    private static BindingFactory instance;

    private BindingFactory() {

    }

    public static synchronized BindingFactory getInstance() {
        return instance = instance == null ? new BindingFactory() : instance;
    }

    public <Binding> Binding getBinding(String strBinding, LayoutInflater inflater) {
        if (strBinding == null)
            throw new NullPointerException("Binding Not Found");

        if (strBinding.equalsIgnoreCase("Login")) {
            ActivityLoginBinding binding = ActivityLoginBinding.inflate(inflater);
            ((Activity) inflater.getContext()).setContentView(binding.getRoot());
            return (Binding) binding;
        }

        throw new NullPointerException("Controller Not Found");
    }
}

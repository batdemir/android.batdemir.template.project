package com.batdemir.template.ui.activities.base.controller;

import androidx.databinding.ViewDataBinding;

@SuppressWarnings({"squid:S00119"})
public class BaseController<Binding extends ViewDataBinding> {

    private Binding binding;

    public BaseController(Binding binding) {
        this.binding = binding;
    }

    protected Binding getBinding() {
        return binding;
    }
}

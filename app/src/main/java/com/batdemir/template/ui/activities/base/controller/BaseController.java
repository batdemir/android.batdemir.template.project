package com.batdemir.template.ui.activities.base.controller;

@SuppressWarnings({"squid:S00119"})
public class BaseController<Binding> {

    private Binding binding;

    public BaseController(Binding binding) {
        this.binding = binding;
    }

    protected Binding getBinding() {
        return binding;
    }
}

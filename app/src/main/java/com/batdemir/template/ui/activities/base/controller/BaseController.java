package com.batdemir.template.ui.activities.base.controller;

public class BaseController<B> {

    private B binding;

    public BaseController(B binding) {
        this.binding = binding;
    }

    protected B getBinding() {
        return binding;
    }
}

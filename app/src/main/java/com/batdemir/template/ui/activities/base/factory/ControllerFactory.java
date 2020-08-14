package com.batdemir.template.ui.activities.base.factory;

import androidx.viewbinding.ViewBinding;

import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.base.controller.BaseController;
import com.batdemir.template.ui.activities.login.LoginController;

public class ControllerFactory {

    private static ControllerFactory instance;

    private ControllerFactory() {

    }

    public static synchronized ControllerFactory getInstance() {
        return instance = instance == null ? new ControllerFactory() : instance;
    }

    @SuppressWarnings("unchecked")
    public <C extends BaseController<?>, B extends ViewBinding> C getController(String controller, B binding) {
        if (controller == null)
            throw new NullPointerException("Controller Not Found");

        if (controller.equalsIgnoreCase("Login"))
            return (C) new LoginController((ActivityLoginBinding) binding);

        throw new NullPointerException("Controller Not Found");
    }
}

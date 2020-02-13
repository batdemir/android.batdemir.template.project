package com.batdemir.template.ui.activities.base.controller;

import com.batdemir.template.ui.activities.login.LoginController;
import com.batdemir.template.databinding.ActivityLoginBinding;

@SuppressWarnings({"squid:S00119", "unchecked"})
public class ControllerFactory {

    private static ControllerFactory instance;

    private ControllerFactory() {

    }

    public static ControllerFactory getInstance() {
        return instance = instance == null ? new ControllerFactory() : instance;
    }

    public <T, Binding> T getController(String controller, Binding binding) {
        if (controller == null)
            throw new NullPointerException("Controller Not Found");

        if (controller.equalsIgnoreCase("Login"))
            return (T) new LoginController((ActivityLoginBinding) binding);


        throw new NullPointerException("Controller Not Found");
    }
}

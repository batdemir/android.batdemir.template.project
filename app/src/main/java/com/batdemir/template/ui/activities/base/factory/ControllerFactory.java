package com.batdemir.template.ui.activities.base.factory;

import com.batdemir.template.databinding.ActivityLoginBinding;
import com.batdemir.template.ui.activities.login.LoginController;

@SuppressWarnings({"squid:S00119", "squid:S1121", "unchecked"})
public class ControllerFactory {

    private static ControllerFactory instance;

    private ControllerFactory() {

    }

    public static synchronized ControllerFactory getInstance() {
        return instance = instance == null ? new ControllerFactory() : instance;
    }

    public <Controller, Binding> Controller getController(String controller, Binding binding) {
        if (controller == null)
            throw new NullPointerException("Controller Not Found");

        if (controller.equalsIgnoreCase("Login"))
            return (Controller) new LoginController((ActivityLoginBinding) binding);


        throw new NullPointerException("Controller Not Found");
    }
}

package com.batdemir.template.helper;

import java.util.Locale;

public class GlobalVariable {
    private static Locale locale = null;

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        GlobalVariable.locale = locale;
    }
}

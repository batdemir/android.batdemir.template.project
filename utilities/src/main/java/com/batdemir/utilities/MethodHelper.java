package com.batdemir.utilities;

import android.content.Context;
import android.content.Intent;

import java.util.regex.Pattern;

public class MethodHelper {

    private static MethodHelper ourInstance = null;

    private MethodHelper() {

    }

    public static MethodHelper getInstance() {
        return ourInstance = ourInstance == null ? new MethodHelper() : ourInstance;
    }

    public String getBaseUrlFormatter(String str) {
        String beginPrefix = "http://";
        String beginPrefixSsl = "https://";
        String endPrefix = "";

        if (str.startsWith(beginPrefix)) str = str.replace(beginPrefix, "");
        if (str.startsWith(beginPrefixSsl)) str = str.replace(beginPrefixSsl, "");

        str = str.replaceAll("[^\\d.:]", "");
        str = beginPrefix + str + endPrefix;
        return str;
    }

    public boolean isNumericValue(String str) {
        if (str == null)
            return false;
        if (str.isEmpty())
            return false;
        return str.matches(Pattern.compile("^-?[0-9]\\d*(\\.\\d+)?$").pattern());
    }

    public void setScanner(Context context, boolean enableScanner) {
        Intent dataWedge = new Intent();
        dataWedge.setAction(context.getString(R.string.data_wedge_default_action));
        dataWedge.putExtra(context.getString(R.string.data_wedge_api_status), enableScanner);
        context.sendBroadcast(dataWedge);
    }
}

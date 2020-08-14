package com.batdemir.utilities;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    public void setScannerV8(Context context, boolean enableScanner) {
        String pluginStatus = context.getString(enableScanner ? R.string.data_wedge_scanner_plugin_resume : R.string.data_wedge_scanner_plugin_suspend);
        Intent dataWedge = new Intent();
        dataWedge.setAction(context.getString(R.string.data_wedge_default_action));
        dataWedge.putExtra(context.getString(R.string.data_wedge_scanner_plugin), pluginStatus);
        context.sendBroadcast(dataWedge);
    }

    @SuppressLint("MissingPermission")
    public void errorProcess(View view) {
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 1f, 0f, -1f, 0f);
        rotate.setRepeatCount(5);
        rotate.setDuration(25);
        rotate.start();
        Vibrator vibrator = (Vibrator) view.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        Objects.requireNonNull(vibrator).vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    public void changeViewToProgress(View view) {
        ProgressBar progressBar = new ProgressBar(view.getContext(), (AttributeSet) null, 16842871);
        progressBar.setLayoutParams(view.getLayoutParams());
        progressBar.setIndeterminate(true);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.addView(progressBar);
        view.setVisibility(View.GONE);
    }

    public Map<String, String> getToken(String token) {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        return header;
    }
}

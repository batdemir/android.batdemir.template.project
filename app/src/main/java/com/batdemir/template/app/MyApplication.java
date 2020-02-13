package com.batdemir.template.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.text.HtmlCompat;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.batdemir.template.R;
import com.batdemir.template.helper.GlobalVariable;
import com.batdemir.template.ui.activities.SplashActivity;
import com.batdemir.utilities.SpecificDialogImp;

import java.util.Locale;

public class MyApplication extends Application {

    private ActivityLifecycleCallbacks activityLifecycleCallbacks;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        GlobalVariable.setLocale(Locale.US);
        MyAlertDialog.setMyAlertDialogCreator(new SpecificDialogImp());
        activityLifecycleCallbacks = getActivityLifecycleCallbacks();
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                setKeepScreen(activity, true);
                setExceptionHandler();
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //Not Implemented
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //Not Implemented
            }

            @Override
            public void onActivityPaused(Activity activity) {
                setKeepScreen(activity, false);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //Not Implemented
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                //Not Implemented
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //Not Implemented
            }
        };
    }

    @SuppressLint("NewApi")
    private void setExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getBaseContext().getString(R.string.exception_last_process_failed));
            stringBuilder.append(getBaseContext().getString(R.string.exception_system_closed_by_unexpected_error));
            stringBuilder.append(getBaseContext().getString(R.string.exception_please_contact_your_manager));
            stringBuilder.append("Exception: ");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                if (!e.getStackTrace()[i].isNativeMethod()) {
                    stringBuilder.append("------------------------------------------------------------");
                    stringBuilder.append("FileName: ").append(e.getStackTrace()[i].getFileName());
                    stringBuilder.append("MethodName: ").append(e.getStackTrace()[i].getMethodName());
                    stringBuilder.append("LineNumber: ").append(e.getStackTrace()[i].getLineNumber());
                }
            }

            Intent crashedIntent = new Intent(getBaseContext(), SplashActivity.class);
            crashedIntent.putExtra("CRASH_REPORT", Html.fromHtml(stringBuilder.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString());
            crashedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, crashedIntent, PendingIntent.FLAG_ONE_SHOT);
            AlarmManager alarmManager = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);

            System.exit(2);
        });
    }

    private void setKeepScreen(Activity activity, boolean isKeep) {
        if (isKeep)
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}

package com.batdemir.template.ui.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.tools.Tool;
import com.batdemir.template.R;
import com.batdemir.template.ui.activities.login.LoginActivity;

public class SplashActivity extends AppCompatActivity implements
        MyAlertDialogButtonListener {

    @Override
    protected void onStart() {
        super.onStart();
        if (checkPermissions() && checkException())
            move();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (permissions.length != 0) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED
                        && grantResults[6] == PackageManager.PERMISSION_GRANTED
                        && grantResults[7] == PackageManager.PERMISSION_GRANTED
                        && grantResults[8] == PackageManager.PERMISSION_GRANTED
                        && grantResults[9] == PackageManager.PERMISSION_GRANTED
                        && grantResults[10] == PackageManager.PERMISSION_GRANTED) {
                    move();
                } else {
                    if (!shouldShowRequestPermissionRationale(permissions[0])
                            || !shouldShowRequestPermissionRationale(permissions[1])
                            || !shouldShowRequestPermissionRationale(permissions[2])
                            || !shouldShowRequestPermissionRationale(permissions[3])
                            || !shouldShowRequestPermissionRationale(permissions[4])
                            || !shouldShowRequestPermissionRationale(permissions[5])
                            || !shouldShowRequestPermissionRationale(permissions[6])
                            || !shouldShowRequestPermissionRationale(permissions[7])
                            || !shouldShowRequestPermissionRationale(permissions[8])
                            || !shouldShowRequestPermissionRationale(permissions[9])
                            || !shouldShowRequestPermissionRationale(permissions[10])) {
                        MyAlertDialog.getInstance(getString(R.string.message_please_activate_the_permissions_in_the_Applications_app_name_permissions_section, getString(R.string.app_name)), MyDialogStyle.INFO).show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_permission));
                    } else {
                        try {
                            Thread.sleep(2000);
                            move();
                        } catch (Exception e) {
                            Log.e(SplashActivity.class.getSimpleName(), e.getMessage());
                        }
                    }
                }

            }
        }
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_permission))) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivity(intent);
            return;
        }
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exception))) {
            move();
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_permission))) {
            Log.d(myAlertDialog.getTag(), "");
            return;
        }
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exception))) {
            Log.d(myAlertDialog.getTag(), "");
        }
    }

    // FUNCTIONS

    private boolean checkPermissions() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE,
                            Manifest.permission.ACCESS_WIFI_STATE,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.BLUETOOTH_ADMIN,
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.VIBRATE},
                    100);
            return false;
        }
        return true;
    }

    private boolean checkException() {
        String result = getIntent().getStringExtra("CRASH_REPORT");
        if (result != null && !result.isEmpty()) {
            MyAlertDialog.getInstance(result, MyDialogStyle.FAILED).show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_exception));
            return false;
        }
        return true;
    }

    private void move() {
        Tool.getInstance(this).move(LoginActivity.class, true, false, null);
    }
}

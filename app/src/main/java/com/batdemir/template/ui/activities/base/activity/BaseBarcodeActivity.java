package com.batdemir.template.ui.activities.base.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.android.batdemir.mydialog.listeners.MyAlertDialogEditTextListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.batdemir.template.R;
import com.batdemir.template.app.MyReceiver;
import com.batdemir.template.app.OnReceivedListener;
import com.batdemir.template.ui.activities.base.controller.BaseController;
import com.batdemir.utilities.MethodHelper;

public abstract class BaseBarcodeActivity<B extends ViewBinding, C extends BaseController<?>> extends BaseActivity<B, C> implements
        MyAlertDialogEditTextListener {

    private MyReceiver<C> myReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myReceiver = new MyReceiver<>(getController());
    }

    @Override
    protected void onResume() {
        super.onResume();
        MethodHelper.getInstance().setScanner(this, true);
        IntentFilter filter = new IntentFilter();
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        filter.addAction(getResources().getString(R.string.data_wedge_intent_filter_action));
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.barcode_receive_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.itemBarcodeReceive) {
            new MyAlertDialog
                    .Builder()
                    .setMessage(getString(R.string.message_please_enter_barcode))
                    .setStyle(MyDialogStyle.INPUT).build()
                    .show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_barcode_dialog));
            return true;
        }
        return false;
    }

    @Override
    public void dialogOkEditText(MyAlertDialog myAlertDialog, EditText editText) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_barcode_dialog))) {
            String result = editText.getText().toString();
            OnReceivedListener onReceivedListener = (OnReceivedListener) getController();
            onReceivedListener.onReceived(result);
        }
    }

    @Override
    public void dialogCancelEditText(MyAlertDialog myAlertDialog, EditText editText) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_barcode_dialog))) {
            Log.v(BaseBarcodeActivity.class.getSimpleName(), String.valueOf(myAlertDialog.getId()));
        }
    }
}

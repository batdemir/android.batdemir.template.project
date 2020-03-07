package com.batdemir.utilities;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.android.batdemir.mydialog.ui.MyDialogStyle;
import com.android.batdemir.mylibrary.connection.Connect;
import com.android.batdemir.mylibrary.tools.ToolConnection;

import retrofit2.Call;

public class SpecificConnect extends Connect {

    protected SpecificConnect() {
        super();
    }

    @Override
    public void connect(Context context, Call call, String operationType) {
        if (!ToolConnection.getInstance(context).isConnected()) {
            String noConnectionMessage = context.getString(R.string.please_check_your_internet_connection);
            MyAlertDialog.getInstance(noConnectionMessage, MyDialogStyle.WARNING).show(((FragmentActivity) context).getSupportFragmentManager(), Connect.class.getSimpleName());
            return;
        }
        new SpecificConnectService(context, operationType).execute(call);
    }
}

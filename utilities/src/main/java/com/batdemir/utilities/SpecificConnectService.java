package com.batdemir.utilities;

import android.annotation.SuppressLint;
import android.content.Context;

import com.android.batdemir.mylibrary.connection.ConnectService;

import retrofit2.Response;

public class SpecificConnectService extends ConnectService {

    @SuppressLint("StaticFieldLeak")
    private Context context;

    public SpecificConnectService(Context context, String operationType) {
        super(context, operationType);
        this.context = context;
    }

    @Override
    protected void onPreProcess() {
        MethodHelper.getInstance().setScanner(context, false);
        super.onPreProcess();
    }

    @Override
    protected void onPostProcess(String operationType, Response response) {
        MethodHelper.getInstance().setScanner(context, true);
        super.onPostProcess(operationType, response);
    }
}

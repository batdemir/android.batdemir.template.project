package com.batdemir.template.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.batdemir.template.R;

@SuppressWarnings({"squid:S00119"})
public class MyReceiver<Controller> extends BroadcastReceiver {

    private Controller controller;

    public MyReceiver(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String receivedBarcode = intent.getStringExtra(context.getString(R.string.data_wedge_intent_key_data));
        OnReceivedListener onReceivedListener = (OnReceivedListener) controller;
        if (onReceivedListener != null) {
            onReceivedListener.onReceived(receivedBarcode);
        }
    }
}

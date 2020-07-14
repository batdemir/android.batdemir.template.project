package com.batdemir.utilities;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.ui.MyAlertDialog;

public class SpecificDialogImp implements MyAlertDialogCreator {

    @Override
    public MyAlertDialog create() {
        return new SpecificDialog();
    }

    public static class SpecificDialog extends MyAlertDialog {

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            if (getContext() != null)
                MethodHelper.getInstance().setScanner(getContext(), false);
        }

        @Override
        public void dismiss() {
            super.dismiss();
            if (getContext() != null)
                MethodHelper.getInstance().setScanner(getContext(), true);
        }
    }
}

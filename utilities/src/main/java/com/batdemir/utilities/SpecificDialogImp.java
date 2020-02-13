package com.batdemir.utilities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.android.batdemir.mydialog.listeners.MyAlertDialogCreator;
import com.android.batdemir.mydialog.ui.MyAlertDialog;

public class SpecificDialogImp implements MyAlertDialogCreator {

    @Override
    public MyAlertDialog create() {
        return new SpecificDialog();
    }

    public static class SpecificDialog extends MyAlertDialog {

        @Override
        public void show(@NonNull FragmentManager manager, @Nullable String tag) {
            super.show(manager, tag);
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

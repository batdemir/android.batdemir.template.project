package com.batdemir.template.ui.fragments.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public abstract class BaseDialogFragment extends DialogFragment implements
        BaseFragmentActions {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return bindView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(this::getObjectReferences);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() == null)
            return;
        getActivity().runOnUiThread(() -> {
            loadData();
            setListeners();
        });
    }
}

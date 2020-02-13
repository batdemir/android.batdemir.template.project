package com.batdemir.template.ui.fragments.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements
        BaseFragmentActions {

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

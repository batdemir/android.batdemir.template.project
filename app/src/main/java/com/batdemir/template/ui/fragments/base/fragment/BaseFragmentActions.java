package com.batdemir.template.ui.fragments.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public interface BaseFragmentActions {
    View bindView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    void getObjectReferences();

    void loadData();

    void setListeners();
}

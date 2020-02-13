package com.batdemir.template.ui.activities.base.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.android.batdemir.mydialog.listeners.MyAlertDialogButtonListener;
import com.android.batdemir.mydialog.ui.MyAlertDialog;
import com.batdemir.template.R;
import com.batdemir.template.ui.activities.base.controller.BaseController;
import com.batdemir.template.ui.activities.base.controller.ControllerFactory;
import com.batdemir.utilities.MethodHelper;

@SuppressWarnings({"squid:S00119"})
public abstract class BaseActivity<Binding extends ViewDataBinding, Controller extends BaseController> extends AppCompatActivity implements
        BaseActions,
        MyAlertDialogButtonListener {

    private boolean isFirstActivity;
    private int layoutId;
    private Binding binding;
    private Controller controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreated();
        create();
    }

    @Override
    protected void onStart() {
        super.onStart();
        runOnUiThread(this::getObjectReferences);
    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(() -> {
            MethodHelper.getInstance().setScanner(this, false);
            loadData();
            setListeners();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destory();
    }

    @Override
    public void onBackPressed() {
        if (isFirstActivity) {
            MyAlertDialog.getInstance(getString(R.string.message_are_you_sure_exit_application), MyAlertDialog.DialogStyle.ACTION).show(getSupportFragmentManager(), getString(R.string.alert_dialog_key_exit));
            return;
        } else {
            finish();
            overridePendingTransition(com.android.batdemir.myresources.R.anim.slide_in_right, com.android.batdemir.myresources.R.anim.slide_out_right);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(com.android.batdemir.myresources.R.anim.slide_in_right, com.android.batdemir.myresources.R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void dialogOk(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exit))) {
            finishAffinity();
            System.exit(0);
        }
    }

    @Override
    public void dialogCancel(MyAlertDialog myAlertDialog) {
        if (myAlertDialog.getTag() == null)
            return;
        if (myAlertDialog.getTag().equals(getString(R.string.alert_dialog_key_exit))) {
            Log.v(BaseActivity.class.getSimpleName(), String.valueOf(myAlertDialog.getId()));
        }
    }

    // FUNCTIONS

    private void create() {
        binding = binding == null ? DataBindingUtil.setContentView(this, layoutId) : binding;
        controller = controller == null ? ControllerFactory.getInstance().getController(this.getClass().getSimpleName().replace("Activity", ""), binding) : controller;
    }

    private void destory() {
        binding = null;
        controller = null;
    }

    protected void init(@LayoutRes int layoutId, String title) {
        this.layoutId = layoutId;
        setTheme(R.style.AppThemeActionBar);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(16f);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void init(@LayoutRes int layoutId, @StyleRes int theme, String title) {
        this.layoutId = layoutId;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(16f);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void init(@LayoutRes int layoutId, @StyleRes int theme, String title, boolean showHomeButton) {
        this.layoutId = layoutId;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(16f);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }

    protected void init(@LayoutRes int layoutId, @StyleRes int theme, String title, float elevation) {
        this.layoutId = layoutId;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void init(@LayoutRes int layoutId, @StyleRes int theme, String title, float elevation, boolean showHomeButton) {
        this.layoutId = layoutId;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }

    protected void init(@LayoutRes int layoutId, @StyleRes int theme, String title, float elevation, boolean showHomeButton, boolean isFirstActivity) {
        this.layoutId = layoutId;
        this.isFirstActivity = isFirstActivity;
        setTheme(theme);
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setElevation(elevation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
    }

    protected Binding getBinding() {
        return binding;
    }

    protected Controller getController() {
        return controller;
    }
}

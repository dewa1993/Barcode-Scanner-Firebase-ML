package com.upadhyde.android.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.upadhyde.android.R;
import com.upadhyde.android.base.activity.AbstractBaseMainActivity;
import com.upadhyde.android.base.activity.AbstractBaseNormalActivity;
import com.upadhyde.android.base.activity.ActivityNavigationController;

import javax.inject.Inject;

public class MainActivity extends AbstractBaseNormalActivity implements AbstractBaseMainActivity.UiInteraction {


    @Inject
    ActivityNavigationController navigationController;

    @Override
    public ActivityNavigationController getNavigationController() {
        return navigationController;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationController.navigateToDashboard();
    }
}

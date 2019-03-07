package com.upadhyde.android.base.activity;


public abstract class AbstractBaseMainActivity extends AbstractBaseNormalActivity {

    public interface UiInteraction {
        ActivityNavigationController getNavigationController();
    }
}

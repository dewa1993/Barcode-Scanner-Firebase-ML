package com.upadhyde.android.base.activity;



import com.upadhyde.android.R;
import com.upadhyde.android.ui.main.activity.MainActivity;
import com.upadhyde.android.ui.main.fragmnet.DashboardFragment;

import javax.inject.Inject;

public class ActivityNavigationController extends AbstractNavigationController {

    @Inject
    ActivityNavigationController(MainActivity mainActivity) {
        super(mainActivity);
    }

    public void navigateToDashboard(){
        changeFragment(DashboardFragment.getInstance(), false);
        updateFragment();
    }

    @Override
    protected int provideContainerId() {
        return R.id.container;
    }
}

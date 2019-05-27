package com.upadhyde.android.base.activity;



import com.upadhyde.android.R;
import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.ui.main.activity.MainActivity;
import com.upadhyde.android.ui.main.fragmnet.DashboardFragment;
import com.upadhyde.android.ui.main.fragmnet.ScannerFragment;
import com.upadhyde.android.ui.main.fragmnet.SplashFragment;

import javax.inject.Inject;

public class ActivityNavigationController extends AbstractNavigationController {

    @Inject
    ActivityNavigationController(MainActivity mainActivity) {
        super(mainActivity);
    }

    public void navigateToDashboard(){
        changeFragment(DashboardFragment.getInstance(), true);
        updateFragment();
    }

    public void navigateToScanner( Input input  ){
        changeFragment(ScannerFragment.getInstance(input), true);
        updateFragment();
    }

    public void navigateToSplash(){
        changeFragment(SplashFragment.getInstance(), false);
        updateFragment();
    }

    @Override
    protected int provideContainerId() {
        return R.id.container;
    }
}

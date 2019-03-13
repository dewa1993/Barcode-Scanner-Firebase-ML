package com.upadhyde.android.di.modules;

import com.upadhyde.android.ui.main.fragmnet.DashboardFragment;
import com.upadhyde.android.ui.main.fragmnet.ScannerFragment;
import com.upadhyde.android.ui.main.fragmnet.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    public abstract DashboardFragment contributeDashboardFragment();

    @ContributesAndroidInjector
    public abstract ScannerFragment contributeScannerFragment();

    @ContributesAndroidInjector
    public abstract SplashFragment contributeSplashFragment();
}

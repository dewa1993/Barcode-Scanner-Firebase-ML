package com.upadhyde.android.di.modules;

import com.upadhyde.android.ui.main.fragmnet.DashboardFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    public abstract DashboardFragment contributeDashboardFragment();
}

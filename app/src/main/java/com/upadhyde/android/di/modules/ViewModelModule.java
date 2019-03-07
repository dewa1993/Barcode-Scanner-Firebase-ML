package com.upadhyde.android.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.upadhyde.android.base.viewmodel.BaseViewModelFactory;
import com.upadhyde.android.di.component.ViewModelKey;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BaseViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(DashboardFragmentViewModel.class)
    public abstract ViewModel bindDashboardFragmentViewModel(DashboardFragmentViewModel dashboardFragmentViewModel);

}

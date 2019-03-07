package com.upadhyde.android.viewmodel.main;

import com.upadhyde.android.base.viewmodel.AbstractViewModel;
import com.upadhyde.android.ui.main.contract.DashboardContract;

import javax.inject.Inject;

public class DashboardFragmentViewModel extends AbstractViewModel<DashboardContract> implements DashboardContract {

    @Inject
    DashboardFragmentViewModel(){

    }

    @Override
    public DashboardContract getViewModel() {
        return this;
    }

    @Override
    public void getData() {

    }
}

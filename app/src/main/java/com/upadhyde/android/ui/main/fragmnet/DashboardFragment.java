package com.upadhyde.android.ui.main.fragmnet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmnetDashboardBinding;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

public class DashboardFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmnetDashboardBinding> {


    public static DashboardFragment getInstance(){
        return new DashboardFragment();
    }

    @Override
    protected Class<DashboardFragmentViewModel> getViewModels() {
        return DashboardFragmentViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragmnet_dashboard;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "Working...", Toast.LENGTH_SHORT).show();
    }
}

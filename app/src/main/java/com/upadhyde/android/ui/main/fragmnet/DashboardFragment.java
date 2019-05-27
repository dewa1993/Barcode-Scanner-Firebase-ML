package com.upadhyde.android.ui.main.fragmnet;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmnetDashboardBinding;
import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.repository.helper.StatusConstant;
import com.upadhyde.android.ui.main.adapter.InputListRecyclerAdapter;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.utils.SharedPreferenceHelper;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;
import java.util.List;
import javax.inject.Inject;

import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.INPUT_FILE_NAME;


public class DashboardFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmnetDashboardBinding>
        implements InputListRecyclerAdapter.InputItemClick {


    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    public static DashboardFragment getInstance() {
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

    private void setView(List<Input> viewList) {
        InputListRecyclerAdapter recyclerAdapter = new InputListRecyclerAdapter(viewList, this);
        getBinding().rvInputList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getBinding().rvInputList.setAdapter(recyclerAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViewModel().getInputList(sharedPreferenceHelper.getString(INPUT_FILE_NAME)).observe(this, listResourcesResponse -> {
            if (listResourcesResponse != null && listResourcesResponse.status == StatusConstant.SUCCESS && listResourcesResponse.data != null) {
                setView(listResourcesResponse.data);
            }
        });
    }

    @Override
    public void itemClick(Input input) {
        getUiInteraction().getNavigationController().navigateToScanner(input);
    }

}

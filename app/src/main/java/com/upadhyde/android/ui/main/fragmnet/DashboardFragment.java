package com.upadhyde.android.ui.main.fragmnet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmnetDashboardBinding;
import com.upadhyde.android.db.Input;
import com.upadhyde.android.repository.helper.StatusConstant;
import com.upadhyde.android.ui.main.adapter.InputListRecyclerAdapter;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;
import java.util.List;


public class DashboardFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmnetDashboardBinding> {


    private static int STORAGE_PERMISSION_CODE = 1;
    private String INPUT_FILE_NAME = "IN_4704_010320191828.txt";

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

    private void getReader(){
       getViewModel().getInputList(INPUT_FILE_NAME).observe(this, listResourcesResponse -> {
           if (listResourcesResponse != null && listResourcesResponse.status == StatusConstant.SUCCESS && listResourcesResponse.data != null) {
               setView(listResourcesResponse.data);
           }
       });
    }

    private void setView(List<Input> viewList){
        InputListRecyclerAdapter recyclerAdapter = new InputListRecyclerAdapter(viewList);
        getBinding().rvInputList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        getBinding().rvInputList.setAdapter(recyclerAdapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestStoragePermission();
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION_CODE);
        }else {
            getReader();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getReader();
            } else {
                Toast.makeText(getContext(),
                        "Oops you just denied the permission",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


}

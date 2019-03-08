package com.upadhyde.android.ui.main.fragmnet;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmnetDashboardBinding;
import com.upadhyde.android.db.Input;
import com.upadhyde.android.repository.helper.StatusConstant;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.utils.ReaderUtils;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
               // do nothing
               Toast.makeText(getContext(), "Aa gyi List -- " + listResourcesResponse.data.toString(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void steView(List<Input> viewList){

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

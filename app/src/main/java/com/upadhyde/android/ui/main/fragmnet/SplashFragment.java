package com.upadhyde.android.ui.main.fragmnet;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmentSplashBinding;
import com.upadhyde.android.repository.helper.StatusConstant;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.utils.SharedPreferenceHelper;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.inject.Inject;


public class SplashFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmentSplashBinding> {

    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    private static final int STORAGE_PERMISSION_CODE = 1;
    private static final int PICK_FILE_RESULT_CODE = 200;
    private static final int HEADER_TEXT_FILE = 223;
    private static final String TEXT_FILE_ROW_SEPERATER = "!";

    public static SplashFragment getInstance() {
        return new SplashFragment();
    }

    @Override
    protected Class<DashboardFragmentViewModel> getViewModels() {
        return DashboardFragmentViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getBinding().btnPermission.setOnClickListener(listener -> {
            if (hasSMSPermission()) {
                performOperation();
            }
        });

        getBinding().btnProceed.setOnClickListener(listener -> getUiInteraction().getNavigationController().navigateToDashboard());
    }

    private boolean hasSMSPermission() {
        String externalStorage = Manifest.permission.READ_EXTERNAL_STORAGE;
        int grantPermission = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), externalStorage);
        String[] permissionList = new String[1];
        if (grantPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList[0] = externalStorage;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(permissionList, STORAGE_PERMISSION_CODE);
            }
            return false;
        } else
            return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                performOperation();
            } else {
                Toast.makeText(getContext(),
                        "Oops you just denied the permission",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    private void performOperation() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(chooseFile, PICK_FILE_RESULT_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            try {
                saveData(data);
            } catch (IOException e) {
                Toast.makeText(getContext(), "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveData(Intent data) throws IOException {
        getViewModel().putFileData(readTextFromUri(data.getData())).observe(this, booleanResourcesResponse -> {
            if (booleanResourcesResponse != null && booleanResourcesResponse.status == StatusConstant.SUCCESS && booleanResourcesResponse.data != null) {
                Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String[] readTextFromUri(Uri uri) throws IOException {
        InputStream inputStream = Objects.requireNonNull(getContext()).getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(TEXT_FILE_ROW_SEPERATER);
        }
        return stringBuilder.toString().substring(HEADER_TEXT_FILE).split(TEXT_FILE_ROW_SEPERATER);
    }

}

package com.upadhyde.android.ui.main.fragmnet;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.upadhyde.android.R;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmentSplashBinding;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.utils.SharedPreferenceHelper;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

import javax.inject.Inject;

import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.INPUT_FILE_NAME;


public class SplashFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmentSplashBinding> {

    @Inject
    SharedPreferenceHelper sharedPreferenceHelper;

    private static int STORAGE_PERMISSION_CODE = 1;
    private static int PICK_FILE_RESULT_CODE = 200;

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

        getBinding().btnPermission.setOnClickListener((listener) -> {
            if (hasSMSPermission()) {
                performOperation();
            }
        });

        getBinding().btnProceed.setOnClickListener((listener) -> {
            getUiInteraction().getNavigationController().navigateToDashboard();
        });
    }

    private boolean hasSMSPermission() {
        String externalStorage = Manifest.permission.READ_EXTERNAL_STORAGE;
        int grantPermission = ContextCompat.checkSelfPermission(getActivity(), externalStorage);
        String[] permissionList = new String[1];
        if (grantPermission != PackageManager.PERMISSION_GRANTED) {
            permissionList[0] = externalStorage;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(permissionList, STORAGE_PERMISSION_CODE);
            }
            return false;
        } else {
            return true;
        }
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
            String fileUrl = data.getData().getPath();
            Toast.makeText(getContext(), "File Selected: " + fileUrl, Toast.LENGTH_SHORT).show();
            sharedPreferenceHelper.putValue(INPUT_FILE_NAME,fileUrl);
        }
    }

    private void copyFile(Intent data) {
//        Uri content_describer = data.getData();
//        String src = content_describer.getPath();
//        File source = new File(src);
//        File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Scanner/Input/" + INPUT_FILE_NAME);
//        if (!destination.exists()) {
//            try {
//                destination.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try (FileChannel in = new FileInputStream(source).getChannel(); FileChannel out = new FileOutputStream(destination).getChannel()) {
//            in.transferTo(0, in.size(), out);
//            getUiInteraction().getNavigationController().navigateToDashboard();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(getContext(), "Error in reading file", Toast.LENGTH_SHORT).show();
//        }
    }

}

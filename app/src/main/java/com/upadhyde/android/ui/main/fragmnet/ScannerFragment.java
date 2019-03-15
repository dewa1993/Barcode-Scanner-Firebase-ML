package com.upadhyde.android.ui.main.fragmnet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.upadhyde.android.R;
import com.upadhyde.android.barcodescannerutil.BarcodeScanningProcessor;
import com.upadhyde.android.barcodescannerutil.common.CameraSource;
import com.upadhyde.android.barcodescannerutil.common.FrameMetadata;
import com.upadhyde.android.barcodescannerutil.common.GraphicOverlay;
import com.upadhyde.android.base.fragmnet.AbstractBaseMainFragment;
import com.upadhyde.android.databinding.FragmnetScannerBinding;
import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.db.table.Output;
import com.upadhyde.android.repository.helper.StatusConstant;
import com.upadhyde.android.ui.main.adapter.OutputListRecyclerViewAdapter;
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

import android.os.Vibrator;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.KEY_CAMERA_PERMISSION_GRANTED;
import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.PERMISSION_REQUEST_CAMERA;

public class ScannerFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmnetScannerBinding> {


    private static final String TAG = "ScannerFragment";
    private List<String> scannedCode = new ArrayList<>();
    private CameraSource mCameraSource = null;
    private Input scannerInput;
    private OutputListRecyclerViewAdapter recyclerAdapter;

    public static ScannerFragment getInstance(Input data) {
        ScannerFragment scannerFragment = new ScannerFragment();
        scannerFragment.scannerInput = data;
        return scannerFragment;
    }

    @Override
    protected Class<DashboardFragmentViewModel> getViewModels() {
        return DashboardFragmentViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragmnet_scanner;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getBinding().tvDescription.setText(scannerInput.getDescription());
        getBinding().tvPlantNoData.setText(String.valueOf(scannerInput.getPlantId()));
        getBinding().tvMaterialNoData.setText(String.valueOf(scannerInput.getMaterialNo()));
        getBinding().tvBatchNoData.setText(String.valueOf(scannerInput.getBatchItemNo()));
        getBinding().tvDeliveryNoData.setText(String.valueOf(scannerInput.getDeliveryNo()));
        getBinding().tvDeliveryItemNoData.setText(String.valueOf(scannerInput.getDeliveryItemNo()));
        getBinding().tvCityData.setText(scannerInput.getCustomerCity());
        getBinding().tvFif0BatchNoData.setText(String.valueOf(scannerInput.getFefoBatchNo()));

        if (Objects.requireNonNull(getActivity()).getWindow() != null) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            Log.e(TAG, "Barcode scanner could not go into fullscreen mode!");
        }

        if (getBinding().preview != null && getBinding().preview.isPermissionGranted(true, mMessageSender))
            new Thread(mMessageSender).start();

        getViewModel().getOutputList(scannerInput.getDeliveryNo()).observe(this, listResourcesResponse -> {
            if (listResourcesResponse != null && listResourcesResponse.status == StatusConstant.SUCCESS && listResourcesResponse.data != null) {
                setView(listResourcesResponse.data);
            }
        });

    }

    private void setView(List<Output> viewList) {
        recyclerAdapter = new OutputListRecyclerViewAdapter(viewList, scannerInput);
        getBinding().rvOutList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getBinding().rvOutList.setAdapter(recyclerAdapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG, "onRequestPermissionsResult: " + requestCode);
        getBinding().preview.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * Restarts the camera.
     */
    @Override
    public void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (getBinding().preview != null)
            getBinding().preview.stop();
    }

    /**
     * Releases the resources associated with the camera source, the associated detector, and the
     * rest of the processing pipeline.
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    private void createCameraSource() {

        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_DATA_MATRIX,
                                FirebaseVisionBarcode.FORMAT_QR_CODE)
                        .build();

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);

        mCameraSource = new CameraSource(getActivity(), getBinding().barcodeOverlay);
        mCameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);

        BarcodeScanningProcessor barcodeScanningProcessor = new BarcodeScanningProcessor(detector);
        barcodeScanningProcessor.setBarcodeResultListener(getBarcodeResultListener());
        mCameraSource.setMachineLearningFrameProcessor(barcodeScanningProcessor);

        startCameraSource();
    }


    private void startCameraSource() {
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Objects.requireNonNull(getContext()).getApplicationContext());

        Log.d(TAG, "startCameraSource: " + code);

        if (code != ConnectionResult.SUCCESS) {
            Dialog dlg =
                    GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), code, PERMISSION_REQUEST_CAMERA);
            dlg.show();
        }

        if (mCameraSource != null && getBinding().preview != null && getBinding().barcodeOverlay != null) {
            try {
                Log.d(TAG, "startCameraSource: ");
                getBinding().preview.start(mCameraSource, getBinding().barcodeOverlay);
            } catch (IOException e) {
                Log.d(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        } else
            Log.d(TAG, "startCameraSource: not started");

    }

    public BarcodeScanningProcessor.BarcodeResultListener getBarcodeResultListener() {
        return new BarcodeScanningProcessor.BarcodeResultListener() {
            @Override
            public void onSuccess(@Nullable Bitmap originalCameraImage, @NonNull List<FirebaseVisionBarcode> barcodes, @NonNull FrameMetadata frameMetadata, @NonNull GraphicOverlay graphicOverlay) {
                for (FirebaseVisionBarcode barCode : barcodes) {

                    if (barCode.getRawValue() != null && barCode.getRawValue().contains("|")) {
                        if (!scannedCode.contains(barCode.getRawValue())) {
                            scannedCode.add(barCode.getRawValue());
                            String[] scannerOut = barCode.getRawValue().split("\\|");
                            if (scannerInput.getMaterialNo() == Long.parseLong(scannerOut[0])) {
                                saveData(scannerOut[1], scannerOut[2]);
                            }
                        } else {
                            alreadyScanned();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Exception e) {

                /*
                 * need to handle this exception with valid results */

            }
        }

                ;

    }

    private void alreadyScanned() {
        // TODO: 3/15/2019 need to show something
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: ");
            if (getBinding().preview != null)
                createCameraSource();

        }
    };

    private final Runnable mMessageSender = () -> {
        Log.d(TAG, "mMessageSender: ");
        Message msg = mHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_CAMERA_PERMISSION_GRANTED, false);
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    };


    private void saveData(String batchNo, String batchItemNo) {
        Output output = new Output(scannerInput.getPlantId(), scannerInput.getDeliveryNo(), scannerInput.getMaterialNo(), scannerInput.getFefoBatchNo(), batchNo, scannerInput.getGtinNumber(), Long.parseLong(batchItemNo));
        getViewModel().saveOutput(output).observe(this, booleanResourcesResponse -> {
            if (booleanResourcesResponse != null && booleanResourcesResponse.status == StatusConstant.SUCCESS && booleanResourcesResponse.data != null) {
                Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(200);
                Toast.makeText(getContext(), "Scan Complete", Toast.LENGTH_SHORT).show();
                recyclerAdapter.updateDataSet(output);
            }
        });
    }
}

package com.upadhyde.android.ui.main.fragmnet;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.upadhyde.android.ui.main.contract.DashboardContract;
import com.upadhyde.android.viewmodel.main.DashboardFragmentViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.KEY_CAMERA_PERMISSION_GRANTED;
import static com.upadhyde.android.barcodescannerutil.BarcodeScanner.Constants.PERMISSION_REQUEST_CAMERA;

public class ScannerFragment extends AbstractBaseMainFragment<DashboardContract, DashboardFragmentViewModel, FragmnetScannerBinding> {


    private String TAG = "ScannerFragment";

    private CameraSource mCameraSource = null;
    private BarcodeScanningProcessor barcodeScanningProcessor;

    public static ScannerFragment getInstance(){
       return new ScannerFragment();
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
        if (Objects.requireNonNull(getActivity()).getWindow() != null) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            Log.e(TAG, "Barcode scanner could not go into fullscreen mode!");
        }

        if (getBinding().preview != null)
            if (getBinding().preview.isPermissionGranted(true, mMessageSender))
                new Thread(mMessageSender).start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

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

        // To initialise the detector

        FirebaseVisionBarcodeDetectorOptions options =
                new FirebaseVisionBarcodeDetectorOptions.Builder()
                        .setBarcodeFormats(
                                FirebaseVisionBarcode.FORMAT_DATA_MATRIX,
                                FirebaseVisionBarcode.FORMAT_QR_CODE)
                        .build();

        FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                .getVisionBarcodeDetector(options);


        // To connect the camera resource with the detector

        mCameraSource = new CameraSource(getActivity(),getBinding().barcodeOverlay);
        mCameraSource.setFacing(CameraSource.CAMERA_FACING_BACK);

        barcodeScanningProcessor = new BarcodeScanningProcessor(detector);
        barcodeScanningProcessor.setBarcodeResultListener(getBarcodeResultListener());

        mCameraSource.setMachineLearningFrameProcessor(barcodeScanningProcessor);

        startCameraSource();
    }


    private void startCameraSource() {

        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getContext().getApplicationContext());

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
                Log.d(TAG, "onSuccess: " + barcodes.size());

                for (FirebaseVisionBarcode barCode : barcodes) {

                    Log.d(TAG, "onSuccess: " + barCode.getRawValue());
                    Log.d(TAG, "onSuccess: " + barCode.getFormat());
                    Log.d(TAG, "onSuccess: " + barCode.getValueType());
                }
            }

            @Override
            public void onFailure(@NonNull Exception e) {

            }
        };

    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            Log.d(TAG, "handleMessage: ");

            if (getBinding().preview  != null)
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
}

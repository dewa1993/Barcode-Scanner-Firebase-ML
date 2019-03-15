package com.upadhyde.android.repository.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.db.dao.ScannerDao;
import com.upadhyde.android.db.table.Output;
import com.upadhyde.android.repository.helper.ResourcesResponse;
import com.upadhyde.android.utils.AppExecutors;
import com.upadhyde.android.utils.ReaderUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainRepositoryImpl implements MainRepository {

    private AppExecutors appExecutors;
    private ScannerDao scannerDao;

    @Inject
    MainRepositoryImpl(AppExecutors appExecutors, ScannerDao scannerDao) {
        this.appExecutors = appExecutors;
        this.scannerDao = scannerDao;
    }

    @Override
    public LiveData<ResourcesResponse<List<Input>>> getInputList(String inputFile) {
        MutableLiveData<ResourcesResponse<List<Input>>> resourcesResponseMutableLiveData = new MutableLiveData<>();

        appExecutors.getDiskOp().execute(() -> {

            List<Input> insertedList = scannerDao.getListOfInput();
            if (insertedList != null) {
                resourcesResponseMutableLiveData.postValue(ResourcesResponse.success(insertedList));
            } else {
                resourcesResponseMutableLiveData.postValue(ResourcesResponse.error("No data found.", null));
            }

        });
        return resourcesResponseMutableLiveData;
    }

    @Override
    public LiveData<ResourcesResponse<Boolean>> saveOutput(Output output) {

        MutableLiveData<ResourcesResponse<Boolean>> responseMutableLiveData = new MutableLiveData<>();

        appExecutors.getDiskOp().execute(() -> {

            if (scannerDao.outputFileData(output) > 0)
                responseMutableLiveData.postValue(ResourcesResponse.success(true));
            else
                responseMutableLiveData.postValue(ResourcesResponse.error("Failed", false));
        });

        return responseMutableLiveData;
    }

    @Override
    public LiveData<ResourcesResponse<List<Output>>> getOutputList(long deliveryNo) {

        MutableLiveData<ResourcesResponse<List<Output>>> resourcesResponseMutableLiveData = new MutableLiveData<>();

        appExecutors.getDiskOp().execute(() -> {

            List<Output> outputList = scannerDao.getListOfOutput(deliveryNo);
            if (outputList != null) {
                resourcesResponseMutableLiveData.postValue(ResourcesResponse.success(outputList));
            } else {
                resourcesResponseMutableLiveData.postValue(ResourcesResponse.error("No Data", null));
            }

        });

        return resourcesResponseMutableLiveData;
    }


    @Override
    public LiveData<ResourcesResponse<Boolean>> putFileData(String[] source) {

        MutableLiveData<ResourcesResponse<Boolean>> responseMutableLiveData = new MutableLiveData<>();
        appExecutors.getDiskOp().execute(() -> {
            List<Input> inputStream = new ArrayList<>();
            if (source != null) {
                for (String inputSource : source) {
                    Input input = new Input();
                    String temp[] = inputSource.split("\\|");
                    if (temp.length == 15) {
                        input.setPlantId(Long.parseLong(temp[0]));
                        input.setDeliveryNo(Long.parseLong(temp[1]));
                        input.setDeliveryType(temp[2]);
                        input.setDeliveryItemNo(Long.parseLong(temp[3]));
                        input.setMaterialNo(Long.parseLong(temp[4]));
                        input.setDescription(temp[5]);
                        input.setGtinNumber(Long.parseLong(temp[6]));
                        input.setBatchItemNo(Long.parseLong(temp[7]));
                        input.setFefoBatchNo(temp[8]);
                        input.setFefoPickedQuantity(temp[9]);
                        input.setSalesUnit(temp[10]);
                        input.setNoOfBoxes(Long.parseLong(temp[11]));
                        input.setConversionFactorUom(Long.parseLong(temp[12]));
                        input.setConversionFactorBoxes(Long.parseLong(temp[13]));
                        input.setCustomerCity(temp[14]);
                        inputStream.add(input);
                    }
                }
            } else {
                responseMutableLiveData.postValue(ResourcesResponse.error("No data found.", false));
            }
            scannerDao.inputFileData(inputStream);
            responseMutableLiveData.postValue(ResourcesResponse.success(true));

        });

        return responseMutableLiveData;
    }
}

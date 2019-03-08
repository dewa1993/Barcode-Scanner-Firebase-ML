package com.upadhyde.android.viewmodel.main;

import android.arch.lifecycle.LiveData;

import com.upadhyde.android.base.viewmodel.AbstractViewModel;
import com.upadhyde.android.db.Input;
import com.upadhyde.android.repository.helper.ResourcesResponse;
import com.upadhyde.android.repository.main.MainRepository;
import com.upadhyde.android.repository.main.MainRepositoryImpl;
import com.upadhyde.android.ui.main.contract.DashboardContract;

import java.util.List;

import javax.inject.Inject;

public class DashboardFragmentViewModel extends AbstractViewModel<DashboardContract> implements DashboardContract {

    private MainRepository mainRepository;

    @Inject
    DashboardFragmentViewModel(MainRepositoryImpl mainRepository){
        this.mainRepository = mainRepository;
    }

    @Override
    public DashboardContract getViewModel() {
        return this;
    }

    @Override
    public LiveData<ResourcesResponse<List<Input>>> getInputList(String inputFile) {
        return mainRepository.getInputList(inputFile);
    }
}

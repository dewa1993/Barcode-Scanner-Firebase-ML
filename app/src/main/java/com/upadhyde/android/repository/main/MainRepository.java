package com.upadhyde.android.repository.main;

import android.arch.lifecycle.LiveData;

import com.upadhyde.android.db.Input;
import com.upadhyde.android.repository.helper.ResourcesResponse;

import java.util.List;

public interface MainRepository {

    LiveData<ResourcesResponse<List<Input>>> getInputList(String inputFile);

}

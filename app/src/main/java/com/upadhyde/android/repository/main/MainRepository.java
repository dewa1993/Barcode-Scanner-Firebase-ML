package com.upadhyde.android.repository.main;

import android.arch.lifecycle.LiveData;

import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.db.table.Output;
import com.upadhyde.android.repository.helper.ResourcesResponse;

import java.util.List;

public interface MainRepository {

    LiveData<ResourcesResponse<List<Input>>> getInputList(String inputFile);

    LiveData<ResourcesResponse<Boolean>> saveOutput(Output output);

    LiveData<ResourcesResponse<List<Output>>> getOutputList(long deliveryNo );

    LiveData<ResourcesResponse<Boolean>> putFileData (String [] data);
}

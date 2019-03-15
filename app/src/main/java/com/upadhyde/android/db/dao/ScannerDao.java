package com.upadhyde.android.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.upadhyde.android.db.table.Input;
import com.upadhyde.android.db.table.Output;

import java.util.List;

@Dao
public interface ScannerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inputFileData(List<Input> inputList);

    @Query("SELECT * FROM Input")
    List<Input> getListOfInput();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long outputFileData(Output output);

    @Query("SELECT * FROM Output WHERE Output.deliveryNo =:deliveryNo")
    List<Output> getListOfOutput(long deliveryNo);


}

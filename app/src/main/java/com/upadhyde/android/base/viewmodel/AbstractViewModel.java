package com.upadhyde.android.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import com.upadhyde.android.base.BaseContract;


public abstract class AbstractViewModel<P extends BaseContract> extends ViewModel {

    public abstract P getViewModel();
}

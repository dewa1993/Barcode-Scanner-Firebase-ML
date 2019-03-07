package com.upadhyde.android.base.fragmnet;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;

import com.upadhyde.android.base.BaseContract;
import com.upadhyde.android.base.activity.AbstractBaseMainActivity;
import com.upadhyde.android.base.viewmodel.AbstractViewModel;


public abstract class AbstractBaseMainFragment<P extends BaseContract,
        VM extends AbstractViewModel<P>, T extends ViewDataBinding>
        extends AbstractBaseDataBindingFragment<P, VM, T> {

    private AbstractBaseMainActivity.UiInteraction uiInteraction;

    public AbstractBaseMainActivity.UiInteraction getUiInteraction() {
        return uiInteraction;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        attach(activity);
    }

    private void attach(Context activityOrContext) {
        uiInteraction = (AbstractBaseMainActivity.UiInteraction) activityOrContext;
        if (uiInteraction == null) {
            throw new NullPointerException("Please extends " + AbstractBaseMainActivity.UiInteraction.class.getName() + " interface to your calling activity");
        }
    }

}

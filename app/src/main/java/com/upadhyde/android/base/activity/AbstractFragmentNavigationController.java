package com.upadhyde.android.base.activity;


import android.support.v4.app.Fragment;

public abstract class AbstractFragmentNavigationController extends BaseNavigationController {

    protected AbstractFragmentNavigationController(Fragment fragment) {
        super(fragment.getChildFragmentManager());
    }
}

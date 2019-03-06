package com.upadhyde.android.base.activity;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public abstract class AbstractNavigationController extends BaseNavigationController {
    private AppCompatActivity activity;
    private Fragment fragmentToChange;
    private boolean isBackStack;

    protected AbstractNavigationController(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        this.activity = activity;
    }

    protected void changeFragment(final Fragment fragment, final boolean addToBackStack) {
        this.fragmentToChange = fragment;
        this.isBackStack = addToBackStack;
    }

    protected void updateFragment() {
        if (this.fragmentToChange == null) {
            return;
        }
        final String backStackName = this.fragmentToChange.getClass().getSimpleName();

        final boolean isPop = getFragmentManager().popBackStackImmediate(backStackName, 0);
        if (!isPop /*&& getFragmentManager().findFragmentByTag(backStackName) == null*/) {
            final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.replace(provideContainerId(), this.fragmentToChange, backStackName);
            if (this.isBackStack) {
                fragmentTransaction.addToBackStack(backStackName);
            }
            fragmentTransaction.commit();
        }
        this.fragmentToChange = null;
        this.isBackStack = false;
    }

    protected void updateFragment(final Fragment fragment, final String backStackName) {
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.replace(provideContainerId(), fragment, backStackName);
        fragmentTransaction.addToBackStack(backStackName);
        fragmentTransaction.commit();
    }

    public void popBackStackInclusive(final String backStackName) {
        getFragmentManager().popBackStackImmediate(backStackName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void popBackStackImmediate(final String backStackName) {
        getFragmentManager().popBackStackImmediate(backStackName, 0);
    }

    public void popBackStackImmediate() {
        getFragmentManager().popBackStackImmediate();
    }

    protected abstract @IdRes
    int provideContainerId();

    public AppCompatActivity getActivity() {
        return activity;
    }
}

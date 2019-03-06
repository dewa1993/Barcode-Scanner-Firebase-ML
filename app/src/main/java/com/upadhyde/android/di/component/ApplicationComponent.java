package com.upadhyde.android.di.component;


import android.app.Application;

import com.upadhyde.android.SampleApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Singleton
@Component(modules = {AndroidInjectionModule.class})
public interface ApplicationComponent {

    void inject(SampleApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}

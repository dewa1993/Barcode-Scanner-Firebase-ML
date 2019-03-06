package com.upadhyde.android.di.component;


import android.app.Application;

import com.upadhyde.android.SampleApplication;
import com.upadhyde.android.di.modules.ActivityModule;
import com.upadhyde.android.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@ApplicationScope
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        ActivityModule.class
})
public interface ApplicationComponent {

    void inject(SampleApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}

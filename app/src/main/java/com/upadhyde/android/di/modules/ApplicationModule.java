package com.upadhyde.android.di.modules;

import android.app.Application;

import com.upadhyde.android.R;
import com.upadhyde.android.di.component.ApplicationScope;
import com.upadhyde.android.network.LiveDataCallAdapterFactory;
import com.upadhyde.android.network.NetworkService;
import com.upadhyde.android.utils.SharedPreferenceHelper;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    /*
    * Room Database provider
    */
//    @ApplicationScope
//    @Provides
//    AppDatabase provideDatabase(Application application) {
//        return Room.databaseBuilder(application, AppDatabase.class, "AppDatabase")
//                .build();
//    }

    /* OkHttp provider */

    @ApplicationScope
    @Provides
    NetworkService provideNetworkService(Application application) {
        return new Retrofit.Builder()
                .baseUrl(application.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(NetworkService.class);
    }

    @ApplicationScope
    @Provides
    SharedPreferenceHelper provideSharedPreference(Application application) {
        return new SharedPreferenceHelper(application);
    }

    /* Dao */

//    @ApplicationScope
//    @Provides
//    RdoManagerDao provideRdoManagerDao(AppDatabase appDatabase) {
//        return appDatabase.provideRdoManagerDao();
//    }

}

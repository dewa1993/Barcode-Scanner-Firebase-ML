package com.upadhyde.android.repository.helper;

import android.arch.lifecycle.LiveData;

import com.upadhyde.android.network.ApiResponse;
import com.upadhyde.android.utils.AppExecutors;


public class RepositoryBuilder<R> {

    private String keyword;
    private AppExecutors appExecutors;
    private LiveData<R> dbSource;
    private LiveData<ApiResponse<R>> networkResponse;
    private RateLimiter<String> rateLimiter;
    private boolean shouldSaveResultToDatabase;

    public RepositoryBuilder(String keyword, AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        this.keyword = keyword;
    }

    AppExecutors getAppExecutors() {
        return appExecutors;
    }

    String getKeyword() {
        return keyword;
    }

    LiveData<R> getDbSource() {
        return dbSource;
    }

    public void setDbSource(LiveData<R> dbSource) {
        this.dbSource = dbSource;
    }

    LiveData<ApiResponse<R>> getNetworkResponse() {
        return networkResponse;
    }

    public void setNetworkResponse(LiveData<ApiResponse<R>> networkResponse) {
        this.networkResponse = networkResponse;
    }

    RateLimiter<String> getRateLimiter() {
        return rateLimiter;
    }

    public void setRateLimiter(RateLimiter<String> rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    boolean isShouldSaveResultToDatabase() {
        return shouldSaveResultToDatabase;
    }

    public void setShouldSaveResultToDatabase(boolean shouldSaveResultToDatabase) {
        this.shouldSaveResultToDatabase = shouldSaveResultToDatabase;
    }
}

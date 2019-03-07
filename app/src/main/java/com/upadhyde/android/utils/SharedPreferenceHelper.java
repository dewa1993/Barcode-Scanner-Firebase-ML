package com.upadhyde.android.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    @Inject
    public SharedPreferenceHelper(Application application) {
        sharedPreferences = application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
    }

    public <T> void putValue(final String key, final T value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value).apply();
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value).apply();
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value).apply();
        }
    }

    public String getString(final String key) {
        return sharedPreferences.getString(key, "");
    }

    public long getLong(final String key) {
        return sharedPreferences.getLong(key, 0);
    }

    public float getFloat(final String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    public boolean getBoolean(final String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getInt(final String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public int getIntFromString(final String key) {
        return Integer.parseInt(sharedPreferences.getString(key, "0"));
    }

    public void clear() {
        sharedPreferences.edit()
                .clear()
                .apply();
    }
}

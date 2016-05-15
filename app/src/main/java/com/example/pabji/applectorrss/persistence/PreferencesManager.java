package com.example.pabji.applectorrss.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.pabji.applectorrss.utils.UrlType;


public class PreferencesManager {

    private static final String SHARED_PREFS_FILE = "Preferences";
    private static final String URL_PREF_KEY = "url";
    private static PreferencesManager instance;
    private SharedPreferences sharedPreferences;
    private Context mContext;
    public Integer urlPreference = 0;

    private SharedPreferences getSettings() {
        return mContext.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
    }


    public static PreferencesManager getInstance(Context context) {
        if(instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    private PreferencesManager() {}

   /* public static @Nullable
    PreferencesManager setContext(Context context) {
        if(instance == null) {
            return null;
        }
        instance.mContext = context;
        instance.sharedPreferences = instance.getSharedPreferences();
        return instance;
    }*/


    public void saveUrlPreference(int urlPreference) {
        this.urlPreference = urlPreference;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(URL_PREF_KEY, urlPreference);
        editor.apply();
    }

    public UrlType getSelectedUrl() {
        if(urlPreference != null) {
            return UrlType.UrlTypeFromIndex(urlPreference);
        }
        urlPreference = sharedPreferences.getInt(URL_PREF_KEY, 0);
        return UrlType.UrlTypeFromIndex(urlPreference);
    }

    public String getUrl(String[] urls){
        if(urlPreference != null){
            return urls[urlPreference];
        }
        return urls[0];
    }
}

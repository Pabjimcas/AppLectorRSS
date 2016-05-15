package com.example.pabji.applectorrss.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.example.pabji.applectorrss.R;

@SuppressLint("ValidFragment")
public class PreferencesFragment extends PreferenceFragment {


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        final ListPreference listPreference = (ListPreference) findPreference("sourceUrl");
        listPreference.setValueIndex(0);
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                listPreference.setValueIndex(Integer.parseInt(newValue.toString()));

                SharedPreferences prefs=getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("url", Integer.valueOf(newValue.toString()));
                editor.apply();
                return false;
            }
        });
    }



}
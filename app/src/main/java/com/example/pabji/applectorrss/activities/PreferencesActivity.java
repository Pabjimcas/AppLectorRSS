package com.example.pabji.applectorrss.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.PreferencesFragment;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        PreferencesFragment preferencesFragment = new PreferencesFragment();
        getFragmentManager().beginTransaction().
                replace(R.id.content_preferences, preferencesFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

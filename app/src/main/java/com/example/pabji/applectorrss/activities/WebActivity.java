package com.example.pabji.applectorrss.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.WebFragment;

public class WebActivity extends AppCompatActivity {

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        WebFragment webFragment = WebFragment.newInstance(url);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_web,webFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

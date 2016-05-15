package com.example.pabji.applectorrss.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.DetailFragment;
import com.example.pabji.applectorrss.fragments.ItemListFragment;
import com.example.pabji.applectorrss.models.Item;
import com.example.pabji.applectorrss.persistence.RSSSQLiteHelper;

public class DetailActivity extends AppCompatActivity {

    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Comprobar si el dispositivo está en landscape para cargar dos fragments en MainActivity y finalizar esta actividad
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {

            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            item = intent.getParcelableExtra("item");
            loadFragment();
        }
    }

    private void loadFragment() {
        //Cargar Fragment
        DetailFragment detailFragment = DetailFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_detail,detailFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

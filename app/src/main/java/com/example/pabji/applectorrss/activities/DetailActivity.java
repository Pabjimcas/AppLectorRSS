package com.example.pabji.applectorrss.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.DetailFragment;
import com.example.pabji.applectorrss.fragments.ItemListFragment;
import com.example.pabji.applectorrss.models.Item;
import com.example.pabji.applectorrss.persistence.RSSSQLiteHelper;

public class DetailActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        item = intent.getParcelableExtra("item");
        loadFragment();
    }

    private void loadFragment() {
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

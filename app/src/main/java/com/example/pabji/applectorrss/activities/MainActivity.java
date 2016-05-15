package com.example.pabji.applectorrss.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.ItemListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemListFragment itemListFragment = ItemListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_item_list,itemListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}

package com.example.pabji.applectorrss.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.fragments.ItemListFragment;
import com.example.pabji.applectorrss.models.Item;
import com.example.pabji.applectorrss.persistence.RSSSQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public SQLiteDatabase db;
    ConnectivityManager conectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
        initDataBase();
    }

    private void loadFragment() {
        ItemListFragment itemListFragment = ItemListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_item_list,itemListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void initDataBase() {
        RSSSQLiteHelper rssHelper = new RSSSQLiteHelper(this, RSSSQLiteHelper.DATABASE_NAME,
                null, RSSSQLiteHelper.DATABASE_VERSION);
        db = rssHelper.getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void saveItemDB(Item item){
        if(item!= null){
            ContentValues newItem = new ContentValues();
            newItem.put(RSSSQLiteHelper.TITLE, item.getTitle());
            newItem.put(RSSSQLiteHelper.DESCRIPTION, item.getDescription());
            newItem.put(RSSSQLiteHelper.CONTENT, item.getContent());
            newItem.put(RSSSQLiteHelper.PUB_DATE, item.getPubDate());
            newItem.put(RSSSQLiteHelper.IMAGE_URL, item.getImageUrl());
            newItem.put(RSSSQLiteHelper.LINK, item.getLink());
            db.insert(RSSSQLiteHelper.DATABASE_TABLE, null, newItem);
        }
    }

    public List<Item> loadItemsDB() {
        String[] campos = new String[]{RSSSQLiteHelper.TITLE,RSSSQLiteHelper.DESCRIPTION,RSSSQLiteHelper.CONTENT, RSSSQLiteHelper.PUB_DATE,RSSSQLiteHelper.IMAGE_URL,RSSSQLiteHelper.LINK};
        Cursor c = db.query(RSSSQLiteHelper.DATABASE_TABLE, campos, null, null, null, null,
                null);

        List<Item> itemList = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                Item item = new Item();
                item.setTitle(c.getString(0));
                item.setDescription(c.getString(1));
                item.setContent(c.getString(2));
                item.setPubDate(c.getString(3));
                item.setImageUrl(c.getString(4));
                item.setLink(c.getString(5));
                itemList.add(item);

            } while (c.moveToNext());
        }
        c.close();
        return itemList;
    }


}

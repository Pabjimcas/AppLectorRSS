package com.example.pabji.applectorrss.utils;

import com.example.pabji.applectorrss.models.Item;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    public static List<Item> filter(List<Item> items, String query) {
        query = query.toLowerCase();
        final List<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            final String title = item.getTitle().toLowerCase();
            if (title.contains(query)) {
                filteredItems.add(item);
            }
        }
        return filteredItems;
    }
}

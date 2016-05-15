package com.example.pabji.applectorrss.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.holders.ItemListHolder;
import com.example.pabji.applectorrss.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListHolder> implements View.OnClickListener {

    private List<Item> listItem;
    private Context mContext;
    private View.OnClickListener listener;

    public ItemListAdapter(Context context, List<Item> items) {
        listItem = items;
        mContext = context;
    }

    @Override
    public ItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        view.setOnClickListener(this);
        return new ItemListHolder(mContext,view);
    }

    @Override
    public void onBindViewHolder(ItemListHolder holder, int position) {
        holder.bindItem(listItem.get(position));
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public void setFilter(List<Item> objects){
        listItem = new ArrayList<>();
        listItem.addAll(objects);
        notifyDataSetChanged();
    }
}

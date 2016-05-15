package com.example.pabji.applectorrss.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.models.Item;
import com.squareup.picasso.Picasso;

public class ItemListHolder extends RecyclerView.ViewHolder {

    private final ImageView imageItem;
    private final TextView titleItem;
    private final TextView descriptionItem;
    private final Context mContext;

    public ItemListHolder(Context context, View itemView) {
        super(itemView);
        imageItem = (ImageView) itemView.findViewById(R.id.image_item);
        titleItem = (TextView)itemView.findViewById(R.id.title_item);
        descriptionItem = (TextView) itemView.findViewById(R.id.description_item);
        mContext = context;
    }

    public void bindItem(Item item) {
        titleItem.setText(item.getTitle());
        descriptionItem.setText(item.getDescription());
        if (item.getImageUrl() != null) {
            Picasso.with(mContext).load(item.getImageUrl()).fit().centerCrop().into(imageItem);
        }else{
            Picasso.with(mContext).load(R.mipmap.ic_launcher).into(imageItem);
        }
    }
}

package com.example.pabji.applectorrss.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.models.Item;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pabji on 15/05/2016.
 */
public class DetailFragment extends Fragment {

    private Item item;

    @Bind(R.id.image_detail)
    ImageView imageDetail;

    @Bind(R.id.title_detail)
    TextView titleDetail;

    @Bind(R.id.description_detail)
    TextView descriptionDetail;

    @Bind(R.id.button_browser)
    Button buttonBrowser;

    public static DetailFragment newInstance(Item item) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        item = getArguments().getParcelable("item");
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        if(getView()!=null) {
            Picasso.with(getContext()).load(item.getImageUrl()).into(imageDetail);
            titleDetail.setText(item.getTitle());
            descriptionDetail.setText(item.getDescription());
            buttonBrowser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                }
            });
        }
    }
}

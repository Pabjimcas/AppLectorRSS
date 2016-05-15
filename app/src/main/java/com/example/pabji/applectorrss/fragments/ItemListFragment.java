package com.example.pabji.applectorrss.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.pabji.applectorrss.R;
import com.example.pabji.applectorrss.activities.DetailActivity;
import com.example.pabji.applectorrss.activities.MainActivity;
import com.example.pabji.applectorrss.adapters.ItemListAdapter;
import com.example.pabji.applectorrss.models.Item;
import com.example.pabji.applectorrss.persistence.RSSSQLiteHelper;
import com.example.pabji.applectorrss.utils.Connectivity;
import com.example.pabji.applectorrss.utils.ParseRSS;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ItemListFragment extends Fragment {

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;



    private static final String URL = "http://elpais.com/rss/tags/andalucia_a.xml";

    public static ItemListFragment newInstance() {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getView()!=null){
            recyclerViewInit();
            if(Connectivity.isNetworkAvailable(getContext())) {
                new RSSAsyncTask().execute(URL);
            }else{
                List<Item> itemList = ((MainActivity)getActivity()).loadItemsDB();
                loadItemsInRecyclerView(itemList);
            }
        }
    }

    private void recyclerViewInit() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private class RSSAsyncTask extends AsyncTask<String, Void, List<Item>> {

        @Override
        protected void onPreExecute() {
            setLoading(true);
        }

        @Override
        protected List<Item> doInBackground(String... params) {
            ParseRSS saxparser = new ParseRSS(params[0]);
            return saxparser.parse();
        }

        @Override
        protected void onPostExecute(final List<Item> items) {

            if(items != null){
                setLoading(false);
                loadItemsInRecyclerView(items);
            }
        }
    }

    private void loadItemsInRecyclerView(final List<Item> items) {
        final ItemListAdapter adapter = new ItemListAdapter(getActivity(), items);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(recyclerView.getChildAdapterPosition(v));
                ((MainActivity)getActivity()).saveItemDB(item);
                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailActivity.class);
                intent.putExtra("item",item);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}

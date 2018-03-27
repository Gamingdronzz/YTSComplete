package com.gamingdronzz.yts.Tabs;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.gamingdronzz.yts.Activity.MovieDetail;
import com.gamingdronzz.yts.Adapter.RecyclerViewAdapterMovieCard;
import com.gamingdronzz.yts.Listeners.ClickListener;
import com.gamingdronzz.yts.Listeners.RecyclerViewTouchListeners;
import com.gamingdronzz.yts.Models.MovieCardModel;
import com.gamingdronzz.yts.Models.MovieData;
import com.gamingdronzz.yts.R;
import com.gamingdronzz.yts.Tools.Helper;
import com.gamingdronzz.yts.Tools.PreferencesManager;
import com.gamingdronzz.yts.Tools.VolleyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//Our class extending fragment
public class Tab1 extends Fragment implements VolleyHelper.VolleyResponse {
    VolleyHelper volleyHelper;
    RecyclerView recyclerView;
    private List<MovieData> modelList;
    RecyclerViewAdapterMovieCard adapter;
    final String TAG = "Tab1";
    PreferencesManager preferencesManager;


    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_1, container, false);
        preferencesManager = new PreferencesManager(view.getContext());
        bindviews(view);
        init(view);
        return view;
    }

    private void init(View view) {
        volleyHelper = new VolleyHelper(this, view.getContext());
        volleyHelper.makeStringRequest(Helper.getInstance().buildQueryByGenre("all", 30), "Upcoming");
        setupProgress();
    }

    private void bindviews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_tab1);

        modelList = new ArrayList<MovieData>();
        adapter = new RecyclerViewAdapterMovieCard(modelList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListeners(view.getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                MovieData movieData = modelList.get(position);
                Helper.getInstance().setSelectedMovieData(movieData);
                preferencesManager.addRecent(movieData.getId(),movieData.getTitle(),movieData.getYear(),movieData.getSmall_cover_image());
                Intent intent = new Intent();
                intent.setClass(view.getContext(),MovieDetail.class);
                view.getContext().startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void setupProgress()
    {


    }
    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        // Check for the rotation
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this.getContext(), "LANDSCAPE", Toast.LENGTH_SHORT).show();
            setupGridLayout(true);

        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this.getContext(), "PORTRAIT", Toast.LENGTH_SHORT).show();
//            if (isTab) {
//                setupGridLayout(true);
//            } else {
//                setupGridLayout(false);
//            }


        }
    }


    private void setupGridLayout(boolean multiColumn) {
//        if (multiColumn) {
//            GridLayoutManager manager = new GridLayoutManager(this.getContext(), 2);
//            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                @Override
//                public int getSpanSize(int position) {
//                    Object ob = adapter.getItem(position);
//                    if (ob instanceof Heading || ob instanceof Add || ob instanceof SimpleText || ob instanceof Divider)
//                        return 2;
//                    else
//                        return 1;
//                }
//            });
//            recyclerView.setLayoutManager(manager);
//        } else {
//            GridLayoutManager manager = new GridLayoutManager(this.getContext(), 1);
//            recyclerView.setLayoutManager(manager);
//        }
    }

    @Override
    public void onError(VolleyError volleyError) {

    }

    @Override
    public void onResponse(String str,String tag) {
        Log.d(TAG, "Response = " + str.toString());
        if (str != null) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                Log.d(TAG, "Status = " + jsonObject.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.STATUS)));
                JSONObject data = new JSONObject(jsonObject.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATA)));
                Log.d(TAG, "Data = " + data.toString());
                JSONArray movies = data.getJSONArray(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIES));
                Log.d(TAG, "Movies = " + movies.toString());
                for (int i = 0; i < movies.length(); i++) {
                    MovieData movieData = Helper.getInstance().createMovieData(movies,i);
                    modelList.add(i, movieData);
                    adapter.notifyItemInserted(i);
                }
            }
            catch (JSONException jse)
            {
                jse.printStackTrace();
            }


//                    movieCardModel.setMovieTitle(movieData.getTitle());
//                    movieCardModel.setImageURL(movieData.getMedium_cover_image());
//                    movieCardModel.setYear(movieData.getYear());

        }

    }


}
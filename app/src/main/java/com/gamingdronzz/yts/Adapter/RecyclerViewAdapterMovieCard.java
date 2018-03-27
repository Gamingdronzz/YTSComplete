package com.gamingdronzz.yts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamingdronzz.yts.Models.MovieData;
import com.gamingdronzz.yts.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapterMovieCard extends RecyclerView.Adapter<RecyclerViewAdapterMovieCard.MyViewHolder> {
    private List<MovieData> movieCardlist;
    final String TAG = "PreviousBusiness";
    Context context;

    public RecyclerViewAdapterMovieCard() {

    }

    public RecyclerViewAdapterMovieCard(List<MovieData> previousBusinesses) {
        this.movieCardlist = previousBusinesses;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_movie, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        context = parent.getContext();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        MovieData model = movieCardlist.get(position);
        if(model==null)
        {
            Log.d(TAG,"Null Model");
            return;
        }
        if(model.getMedium_cover_image()!=null) {
            Picasso.get()
                    .load(model.getMedium_cover_image())
                    .into(holder.imageCover);
        }
        holder.movieTitle.setText(model.getTitle());
        holder.movieReleaseYear.setText(model.getYear());
    }


    @Override
    public int getItemCount() {
        return movieCardlist == null ? 0 : movieCardlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCover;
        private TextView movieTitle;
        private TextView movieReleaseYear;



        private MyViewHolder(View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.image_cover_small);
            movieTitle = itemView.findViewById(R.id.movie_name);
            movieReleaseYear = itemView.findViewById(R.id.movie_year);
        }

    }
}



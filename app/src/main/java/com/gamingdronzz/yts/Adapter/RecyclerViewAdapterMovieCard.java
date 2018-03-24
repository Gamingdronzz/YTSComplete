package com.gamingdronzz.yts.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gamingdronzz.yts.Models.MovieCardModel;
import com.gamingdronzz.yts.R;
import com.gamingdronzz.yts.Tools.VolleyHelper;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapterMovieCard extends RecyclerView.Adapter<RecyclerViewAdapterMovieCard.MyViewHolder> {
    private List<MovieCardModel> movieCardlist;
    final String TAG = "PreviousBusiness";
    Context context;

    public RecyclerViewAdapterMovieCard() {

    }

    public RecyclerViewAdapterMovieCard(List<MovieCardModel> previousBusinesses) {
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
        MovieCardModel model = movieCardlist.get(position);
        Picasso.get()
                .load(model.getImageURL())
                .into(holder.imageCover);
        holder.movieTitle.setText(model.getMovieName());
    }


    @Override
    public int getItemCount() {
        return movieCardlist == null ? 0 : movieCardlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCover;
        private TextView movieTitle;

        private MyViewHolder(View itemView) {
            super(itemView);
            imageCover = (ImageView) itemView.findViewById(R.id.image_cover_small);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_name);
        }

    }
}


package com.gamingdronzz.yts.Adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gamingdronzz.yts.Models.MovieCardModel;
import com.gamingdronzz.yts.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapterRight extends RecyclerView.Adapter<RecyclerViewAdapterRight.MyViewHolder> {
    private List<MovieCardModel> movieCardlist;
    final String TAG = "PreviousBusiness";
    Context context;

    public RecyclerViewAdapterRight() {

    }

    public RecyclerViewAdapterRight(List<MovieCardModel> previousBusinesses) {
        this.movieCardlist = previousBusinesses;

    }

    public void ChangeList(List<MovieCardModel> movieCardlist)
    {
        this.movieCardlist = movieCardlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_movie_right, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        context = parent.getContext();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MovieCardModel model = movieCardlist.get(position);
        Picasso.get()
                .load(model.getMovieCoverURL())
                .into(holder.imageCover);
        holder.movieTitle.setText(model.getMovieTitle());
        holder.movieTime.setText(model.getMovieTime());
        holder.movieReleaseYear.setText(model.getMovieReleaseYear());
    }


    @Override
    public int getItemCount() {
        return movieCardlist == null ? 0 : movieCardlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageCover;
        private TextView movieTitle;
        private TextView movieReleaseYear;
        private TextView movieTime;


        private MyViewHolder(View itemView) {
            super(itemView);
            imageCover = (ImageView) itemView.findViewById(R.id.image_cover_small_right);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_name_right);
            movieReleaseYear = itemView.findViewById(R.id.movie_year_right);
            movieTime = itemView.findViewById(R.id.movie_time);
        }

    }
}



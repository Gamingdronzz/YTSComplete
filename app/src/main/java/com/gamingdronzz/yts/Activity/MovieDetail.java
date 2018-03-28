package com.gamingdronzz.yts.Activity;

import android.content.res.ColorStateList;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.gamingdronzz.yts.Models.MovieCardModel;
import com.gamingdronzz.yts.Models.MovieData;
import com.gamingdronzz.yts.R;
import com.gamingdronzz.yts.Tools.Helper;
import com.gamingdronzz.yts.Tools.PreferencesManager;
import com.gamingdronzz.yts.Tools.VolleyHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetail extends AppCompatActivity implements VolleyHelper.VolleyResponse {

    MovieData movieData;
    PreferencesManager preferencesManager;
    ImageView imageViewBackground, imageViewMediumCoverDetail;
    TextView textViewMovieTitle, textViewMovieGenre, textViewReleaseYear, textViewIMDBRating;
    final String TAG = "MovieDetails";
    VolleyHelper volleyHelper;
    boolean favorite = false;
    MenuItem favItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieData = Helper.getInstance().getSelectedMovieData();
        preferencesManager = new PreferencesManager(this);
        volleyHelper = new VolleyHelper(this, this);
        favorite = preferencesManager.checkForFavorite(Helper.getInstance().getSelectedMovieID());
        bindViews();
        if (movieData == null) {
            fetchMovieDetails(Helper.getInstance().getSelectedMovieID());
        } else {
            setupMovieDetails(movieData);
        }


    }


    private void fetchMovieDetails(String selectedMovieID) {
        volleyHelper.makeStringRequest(Helper.getInstance().buildQueryByMovieID(selectedMovieID), "Detail-" + selectedMovieID);
    }

    private void setupMovieDetails(MovieData movieData) {
        textViewMovieTitle.setText(movieData.getTitle());
        textViewReleaseYear.setText(movieData.getYear());
        textViewIMDBRating.setText(movieData.getRating());
//        textViewMovieGenre.setText(movieData.getGenres().toString());


        Picasso.get()
                .load(movieData.getBackground_image_original())
                .into(imageViewBackground);

        Picasso.get()
                .load(movieData.getMedium_cover_image())
                .into(imageViewMediumCoverDetail);
    }

    private void bindViews() {
        imageViewBackground = findViewById(R.id.background);
        imageViewMediumCoverDetail = findViewById(R.id.image_cover_medium_detail);


        textViewMovieTitle = findViewById(R.id.movie_title_detail);
        textViewMovieGenre = findViewById(R.id.movie_genre_detail);
        textViewReleaseYear = findViewById(R.id.movie_release_year_detail);
        textViewIMDBRating = findViewById(R.id.movie_imdbrating_detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        favItem = menu.findItem(R.id.action_favorites);
        // set your desired icon here based on a flag if you like
        if(favorite)
            favItem.setIcon(R.drawable.ic_favorite_black_24dp);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the FragmentHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_favorites:
                if(favorite) {
                    if (movieData != null) {
                        addtoFavorites(movieData);
                    } else {
                        volleyHelper.makeStringRequest(Helper.getInstance().buildQueryByMovieID(Helper.getInstance().getSelectedMovieID()), "FavDetail-" + Helper.getInstance().getSelectedMovieID());
                    }
                }
                else
                {
                    switchFavIcon(false);
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void switchFavIcon(boolean isFavorite)
    {
        if(isFavorite)
        {
            favItem.setIcon(R.drawable.ic_favorite_black_24dp);
        }
        else
            favItem.setIcon(R.drawable.ic_favorite_border_black_24dp);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Helper.getInstance().setSelectedMovieData(null);
    }
    private void addtoFavorites(MovieData movieData)
    {
        MovieCardModel movieCardModel = new MovieCardModel();
        movieCardModel.setMovieTitle(movieData.getTitle());
        movieCardModel.setMovieCoverURL(movieData.getSmall_cover_image());
        movieCardModel.setMovieReleaseYear(movieData.getYear());
        movieCardModel.setMovieTime(Helper.getInstance().getTime());
        preferencesManager.addFavorite(movieCardModel);
        switchFavIcon(true);
    }

    @Override
    public void onError(VolleyError volleyError) {

    }

    @Override
    public void onResponse(String str, String tag) {
        Log.d(TAG, "Response = " + str.toString());
        if (str != null) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                JSONObject data = new JSONObject(jsonObject.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATA)));
                JSONObject movie = data.getJSONObject(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIE));
                MovieData movieData = Helper.getInstance().createMovieData(movie);
                setupMovieDetails(movieData);
                if(tag.contains("FavDetail-"))
                {
                    Log.d(TAG,"Adding to favorite after fetching movie data from fav button");
                    addtoFavorites(movieData);
                    switchFavIcon(true);
                }
            } catch (JSONException jse) {
                jse.printStackTrace();
            }
        }
    }
}

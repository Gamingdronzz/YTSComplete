package com.gamingdronzz.yts.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.util.Log;

import com.gamingdronzz.yts.Models.MovieCardModel;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by balpreet on 3/27/2018.
 */

public class PreferencesManager {

    private HashSet<String> RecentMovies;
    private HashSet<String> FavoriteMovies;

    private Context context;

    final String MYPREFS = "MyPreferences";

    public PreferencesManager(Context context) {
        this.context = context;

    }

    final String RECENT_MOVIE = "RECENT_MOVIE";
    final String FAVORITE_MOVIE = "FAVORITE_MOVIE";

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(MYPREFS, context.MODE_PRIVATE);
    }

    public ArrayList<MovieCardModel> getFavorites() {
        Log.d("PreferencesManager", "Getting Favorites");
        Set<String> favoriteMovies = getSharedPreferences().getStringSet(FAVORITE_MOVIE, new HashSet<String>());
        ArrayList<MovieCardModel> movieCardModelArrayList = new ArrayList<>();
        for (Object s :  favoriteMovies.toArray()) {
            String movie = (String) s;
            movieCardModelArrayList.add(getMovieCardFromString(movie));
        }
        return  movieCardModelArrayList;
    }

    public ArrayList<MovieCardModel> getRecents() {
        Log.d("PreferencesManager", "Getting Recents");
        Set<String> recentMovies = getSharedPreferences().getStringSet(RECENT_MOVIE, new HashSet<String>());
        ArrayList<MovieCardModel> movieCardModelArrayList = new ArrayList<>();
        for (Object s :  recentMovies.toArray()) {
            String movie = (String) s;
            movieCardModelArrayList.add(getMovieCardFromString(movie));
        }
        return  movieCardModelArrayList;
    }

    public void addFavorite(MovieCardModel movieCardModel) {
        Log.d("PreferencesManager", "Movie ID = " + movieCardModel.getMovieID());
        FavoriteMovies = (HashSet<String>) getSharedPreferences().getStringSet(FAVORITE_MOVIE,new HashSet<String>());
        RecentMovies = (HashSet<String>) getSharedPreferences().getStringSet(RECENT_MOVIE,new HashSet<String>());
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        if (checkForRepitition(FavoriteMovies, movieCardModel.getMovieID())) {
            String movie = getCommaSeparatedString(movieCardModel);
            FavoriteMovies.add(movie);
            editor.putStringSet(FAVORITE_MOVIE,FavoriteMovies);
            editor.putStringSet(RECENT_MOVIE,RecentMovies);
            editor.commit();
        }
    }

    public void addRecent(MovieCardModel movieCardModel) {
        Log.d("PreferencesManager", "Movie ID = " + movieCardModel.getMovieID());
        movieCardModel.setMovieTime(Helper.getInstance().getTime());
        RecentMovies = (HashSet<String>) getSharedPreferences().getStringSet(RECENT_MOVIE,new HashSet<String>());
        FavoriteMovies = (HashSet<String>) getSharedPreferences().getStringSet(FAVORITE_MOVIE,new HashSet<String>());
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        if (checkForRepitition(RecentMovies, movieCardModel.getMovieID())) {
            String movie = getCommaSeparatedString(movieCardModel);
            RecentMovies.add(movie);
            editor.putStringSet(RECENT_MOVIE,RecentMovies);
            editor.putStringSet(FAVORITE_MOVIE,FavoriteMovies);
            editor.commit();
        }

    }

    public String getTime() {
        return "default";
    }

    private boolean checkForRepitition(HashSet<String> stringHashSet, String id) {
        int i = 0;
        while (i < stringHashSet.size()) {
            String movie = stringHashSet.iterator().next();
            MovieCardModel movieCardModel = getMovieCardFromString(movie);
            if(movieCardModel.getMovieID() == id) {
                return false;
            }
            i++;
        }
        return true;
    }

    private String getCommaSeparatedString(MovieCardModel movieCardModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(movieCardModel.getMovieID());
        builder.append(",");
        builder.append(movieCardModel.getMovieTitle());
        builder.append(",");
        builder.append(movieCardModel.getMovieTime());
        builder.append(",");
        builder.append(movieCardModel.getMovieReleaseYear());
        builder.append(",");
        builder.append(movieCardModel.getMovieCoverURL());
        return builder.toString();
    }

    private MovieCardModel getMovieCardFromString(String input) {
        String[] strings = input.split(",");
        MovieCardModel movieCardModel = new MovieCardModel();
        movieCardModel.setMovieID(strings[0]);
        movieCardModel.setMovieTitle(strings[1]);
        movieCardModel.setMovieTime(strings[2]);
        movieCardModel.setMovieReleaseYear(strings[3]);
        movieCardModel.setMovieCoverURL(strings[4]);
        return movieCardModel;

    }

    public boolean checkForFavorite(String id)
    {
        boolean result = false;
        ArrayList<MovieCardModel> favorites = getFavorites();
        for (MovieCardModel model :
                favorites) {
            Log.d("FAV" ,"Fav = " + model.getMovieID() + "-"+ id );
            if (model.getMovieID().equals(id)) {
                result = true;
            }
        }
        Log.d("Fav","Fav = " + result);
        return result;
    }
}

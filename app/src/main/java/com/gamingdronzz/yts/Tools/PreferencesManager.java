package com.gamingdronzz.yts.Tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.sql.Struct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by balpreet on 3/27/2018.
 */

public class PreferencesManager {

    private HashSet<String> RecentMovieID;
    private HashSet<String> RecentMovieTitle;
    private HashSet<String> RecentMovieYear;
    private HashSet<String> RecentMovieTime;
    private HashSet<String> RecentMovieCoverURL;

    private HashSet<String> FavoriteMovieID;
    private HashSet<String> FavoriteMovieTitle;
    private HashSet<String> FavoriteMovieYear;
    private HashSet<String> FavoriteMovieTime;
    private HashSet<String> FavoriteMovieCoverURL;
    private Context context;

final String MYPREFS = "MyPreferences";
    public PreferencesManager(Context context) {
        this.context = context;

    }


    final String RECENT_MOVIE_ID = "RECENT_MOVIE_ID";
    final String RECENT_MOVIE_TITLE = "RECENT_MOVIE_TITLE";
    final String RECENT_MOVIE_YEAR = "RECENT_MOVIE_YEAR";
    final String RECENT_MOVIE_TIME = "RECENT_MOVIE_TIME";
    final String RECENT_MOVIE_COVER_URL = "RECENT_MOVIE_COVER_URL";


    final String FAVORITE_MOVIE_ID = "FAVORITE_MOVIE_ID";
    final String FAVORITE_MOVIE_TITLE = "FAVORITE_MOVIE_TITLE";
    final String FAVORITE_MOVIE_YEAR = "FAVORITE_MOVIE_YEAR";
    final String FAVORITE_MOVIE_TIME = "FAVORITE_MOVIE_TIME";
    final String FAVORITE_MOVIE_COVER_URL = "FAVORITE_MOVIE_COVER_URL";

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(MYPREFS,context.MODE_PRIVATE);
    }

    public Object[][] getFavorites() {
        Set<String> setMovieID = getSharedPreferences().getStringSet(FAVORITE_MOVIE_ID, new HashSet<String>());
        Set<String> setMovieTitle = getSharedPreferences().getStringSet(FAVORITE_MOVIE_TITLE, new HashSet<String>());
        Set<String> setMovieYear = getSharedPreferences().getStringSet(FAVORITE_MOVIE_YEAR, new HashSet<String>());
        Set<String> setMovieTime = getSharedPreferences().getStringSet(FAVORITE_MOVIE_TIME, new HashSet<String>());
        Set<String> setMovieCoverURL = getSharedPreferences().getStringSet(FAVORITE_MOVIE_COVER_URL, new HashSet<String>());
        Object[][] objects = new Object[5][];
        objects[0] = new Object[]{setMovieID.toArray()};
        objects[1] = new Object[]{setMovieTitle.toArray()};
        objects[2] = new Object[]{setMovieYear.toArray()};
        objects[3] = new Object[]{setMovieTime.toArray()};
        objects[4] = new Object[]{setMovieCoverURL.toArray()};
        return objects;
    }

    public Object[][] getRecents() {
        Log.d("PreferencesManager", "Getting Recents");
        Set<String> setMovieID = getSharedPreferences().getStringSet(RECENT_MOVIE_ID, new HashSet<String>());
        Set<String> setMovieTitle = getSharedPreferences().getStringSet(RECENT_MOVIE_TITLE, new HashSet<String>());
        Set<String> setMovieYear = getSharedPreferences().getStringSet(RECENT_MOVIE_YEAR, new HashSet<String>());
        Set<String> setMovieTime = getSharedPreferences().getStringSet(RECENT_MOVIE_TIME, new HashSet<String>());
        Set<String> setMovieCoverURL = getSharedPreferences().getStringSet(RECENT_MOVIE_COVER_URL, new HashSet<String>());
        Object[][] objects = new Object[5][];
        objects[0] = setMovieID.toArray();
        objects[1] = setMovieTitle.toArray();
        objects[2] = setMovieYear.toArray();
        objects[3] = setMovieTime.toArray();
        objects[4] = setMovieCoverURL.toArray();

        if(objects[0][0]!=null)
        {
            Log.d("PREF","ID = " + objects[0][0].toString());
        }

        return objects;
    }

    public void addFavorite(String movieID,String movieTitle,String movieReleaseYear,String movieCoverURL) {

        SharedPreferences.Editor editor = getSharedPreferences().edit();
        FavoriteMovieID = (HashSet) getSharedPreferences().getStringSet(FAVORITE_MOVIE_ID,new HashSet<String>());
        if (checkForRepitition(FavoriteMovieID, movieID)) {
            FavoriteMovieID.add(movieID);
            FavoriteMovieTitle.add(movieTitle);
            FavoriteMovieYear.add(movieReleaseYear);
            FavoriteMovieTime.add(getTime());
            FavoriteMovieCoverURL.add(movieCoverURL);
            editor.putStringSet(FAVORITE_MOVIE_ID, FavoriteMovieID);
            editor.putStringSet(FAVORITE_MOVIE_TITLE, FavoriteMovieTitle);
            editor.putStringSet(FAVORITE_MOVIE_YEAR, FavoriteMovieYear);
            editor.putStringSet(FAVORITE_MOVIE_TIME, FavoriteMovieTime);
            editor.putStringSet(FAVORITE_MOVIE_COVER_URL, FavoriteMovieCoverURL);
            editor.commit();
        }

    }

    public void addRecent(String movieID,String movieTitle,String movieReleaseYear,String movieCoverURL) {
        Log.d("PreferencesManager", "Movie ID = " + movieID);
        RecentMovieID = (HashSet) getSharedPreferences().getStringSet(RECENT_MOVIE_ID,new HashSet<String>());
        RecentMovieTitle = (HashSet) getSharedPreferences().getStringSet(RECENT_MOVIE_TITLE,new HashSet<String>());
        RecentMovieTime = (HashSet) getSharedPreferences().getStringSet(RECENT_MOVIE_TIME,new HashSet<String>());
        RecentMovieYear = (HashSet) getSharedPreferences().getStringSet(RECENT_MOVIE_YEAR,new HashSet<String>());
        RecentMovieCoverURL = (HashSet) getSharedPreferences().getStringSet(RECENT_MOVIE_COVER_URL,new HashSet<String>());

        
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        if (checkForRepitition(RecentMovieID, movieID)) {
            RecentMovieID.add(movieID);
            RecentMovieTitle.add(movieTitle);
            RecentMovieYear.add(movieReleaseYear);
            RecentMovieTime.add(getTime());
            RecentMovieCoverURL.add(movieCoverURL);
            editor.putStringSet(RECENT_MOVIE_ID, RecentMovieID);
            editor.putStringSet(RECENT_MOVIE_TITLE, RecentMovieTitle);
            editor.putStringSet(RECENT_MOVIE_YEAR, RecentMovieYear);
            editor.putStringSet(RECENT_MOVIE_TIME, RecentMovieTime);
            editor.putStringSet(RECENT_MOVIE_COVER_URL, RecentMovieCoverURL);
            editor.commit();
        }
    }

    public String getTime()
    {
        return  "default";
    }
    private boolean checkForRepitition(HashSet<String> stringHashSet, String id) {
        int i=0;
        while(i<stringHashSet.size())
        {
            if (stringHashSet.iterator().next() == id)
                return false;
            i++;
        }
        return true;
    }
}

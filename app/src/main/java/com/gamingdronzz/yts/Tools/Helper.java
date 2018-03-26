package com.gamingdronzz.yts.Tools;

import android.util.Log;

import com.gamingdronzz.yts.App.AppController;
import com.gamingdronzz.yts.Models.MovieData;
import com.gamingdronzz.yts.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Struct;
import java.util.HashMap;

/**
 * Created by balpreet on 3/22/2018.
 */

public class Helper {

    private static Helper _instance;
    final String TAG = "Helper";

    public final String DATA = "data";
    public final String STATUS = "status";
    public final String STATUS_MESSAGE = "status_message";
    public final String MOVIES = "movies";

    public enum SortParam {
        TITLE(R.string.title),
        YEAR(R.string.year),
        RATING(R.string.rating),
        PEERS(R.string.peers),
        SEEDS(R.string.seeds),
        DOWNLOADS(R.string.downloads),
        LIKES(R.string.likes),
        DATE_ADDED(R.string.date_added);

        private int param;

        SortParam(int param) {
            this.param = param;
        }
    }


    public String getString(int id) {
        return AppController.getInstance().getApplicationContext().getString(id);
    }

    private Helper() {
    }

    public static Helper getInstance() {
        if (_instance == null) {
            _instance = new Helper();
        }
        return _instance;
    }

    public String getBaseURL() {
        return "https://yts.am";
    }

    public String getAPIURL() {
        return "https://yts.am/api/v2/";
    }

    public JSONObject getJson(String input) {
        String json = input.substring(input.indexOf("{"), input.indexOf("}"));
        JSONObject result = null;
        try {
            result = new JSONObject(json);
        } catch (JSONException jse) {
            jse.printStackTrace();
            Log.v(TAG, "Error creating json");
        }
        //Log.v(TAG, result.toString());
        return result;
    }

    public String buildQueryByGenre(String genre, int limit) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(getAPIURL())
                .append("list_movies.json")
                .append("?genre=" + genre)
                .append("&limit=" + limit);
        Log.d(TAG, "Query = " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String buildQueryByGenreAndSort(String genre, int limit, SortParam sortParam) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(getAPIURL())
                .append("list_movies.json")
                .append("?genre=" + genre)
                .append("&limit=" + limit)
                .append("&sort_by=" + getString(sortParam.param));
        Log.d(TAG, "Query = " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String buildQueryByMovieID(String id) {
        return null;
    }
}

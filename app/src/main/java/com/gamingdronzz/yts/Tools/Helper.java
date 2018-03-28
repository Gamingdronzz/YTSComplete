package com.gamingdronzz.yts.Tools;

import android.util.Log;

import com.gamingdronzz.yts.App.AppController;
import com.gamingdronzz.yts.Models.MovieData;
import com.gamingdronzz.yts.Models.TorrentData;
import com.gamingdronzz.yts.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by balpreet on 3/22/2018.
 */

public class Helper {

    private static Helper _instance;
    private MovieData selectedMovieData = null;
    private String selectedMovieID;
    final String TAG = "Helper";

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

    public enum API_KEY {
        STATUS(R.string.status),
        STATUS_MESSAGE(R.string.status_message),
        DATA(R.string.data),
        MOVIE_COUNT(R.string.movie_count),
        LIMIT(R.string.limit),
        PAGE_NUMBER(R.string.page_number),
        MOVIES(R.string.movies),
        MOVIE(R.string.movie),
        ID(R.string.movie_id),
        MOVIE_URL(R.string.movie_url),
        IMDB_CODE(R.string.imdb_code),
        TITLE(R.string.movie_title),
        TITLE_ENGLISH(R.string.movie_title_english),
        TITLE_LONG(R.string.movie_title_long),
        SLUG(R.string.slug),
        YEAR(R.string.movie_year),
        RATING(R.string.rating),
        RUNTIME(R.string.runtime),
        GENRES(R.string.genres),
        SUMMARY(R.string.summary),
        DESCRIPTION_FULL(R.string.description_full),
        SYNOPSIS(R.string.synopsis),
        YT_TRAILER_CODE(R.string.yt_trailer_code),
        LANGUAGE(R.string.language),
        MPA_RATING(R.string.mpa_rating),
        BACKGROUND_IMAGE(R.string.background_image),
        BACKGROUND_IMAGE_ORIGINAL(R.string.background_image_original),
        SMALL_COVER_IMAGE(R.string.small_cover_image),
        MEDIUM_COVER_IMAGE(R.string.medium_cover_image),
        LARGE_COVER_IMAGE(R.string.large_cover_image),
        STATE(R.string.movie_state),
        TORRENTS(R.string.torrents),
        TORRENT_URL(R.string.torrent_url),
        TORRENT_HASH(R.string.torrent_hash),
        TORRENT_QUALITY(R.string.torrent_quality),
        TORRENT_SEEDS(R.string.torrent_seeds),
        TORRENT_PEERS(R.string.torrent_peers),
        TORRENT_SIZE(R.string.torrent_size),
        TORRENT_SIZE_BYTES(R.string.torrent_size_bytes),
        TORRENT_DATE_UPLOADED(R.string.torrent_date_uploaded),
        TORRENT_DATE_UPLOADED_UNIX(R.string.torrent_date_uploaded_unix),
        DATE_UPLOADED(R.string.torrent_date_uploaded),
        DATE_UPLOADED_UNIX(R.string.torrent_date_uploaded_unix);

        private int param;

        API_KEY(int param) {
            this.param = param;
        }
    }


    public String getString(int id) {
        return AppController.getInstance().getApplicationContext().getString(id);
    }

    public String getSelectedMovieID()
    {
        return this.selectedMovieID;
    }

    public void setSelectedMovieID(String selectedMovieID)
    {
        this.selectedMovieID = selectedMovieID;
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
        return "https://yts.am/api/v2/movie_details.json?movie_id=" + id;
    }

    public void setSelectedMovieData(MovieData movieData) {
        this.selectedMovieData = movieData;
        if(movieData!=null)
        setSelectedMovieID(movieData.getId());
    }

    public MovieData getSelectedMovieData() {
        return selectedMovieData;
    }

    public String getTorrentAPIKey(API_KEY api_key) {
        return getString(api_key.param);
    }

    public MovieData createMovieData(JSONArray movies, int i) {
        try {
            MovieData movieData = new MovieData();
            JSONObject movie = new JSONObject(movies.get(i).toString());
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.ID))) {
                movieData.setId(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.ID)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIE_URL))) {
                movieData.setMovieURL(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIE_URL)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.IMDB_CODE))) {
                movieData.setImdb_code(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.IMDB_CODE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE))) {
                movieData.setTitle(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_ENGLISH))) {
                movieData.setTitle_english(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_ENGLISH)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_LONG))) {
                movieData.setTitle_long(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_LONG)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SLUG))) {
                movieData.setSlug(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SLUG)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YEAR))) {
                movieData.setYear(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YEAR)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RATING))) {
                movieData.setRating(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RATING)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RUNTIME))) {
                movieData.setRuntime(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RUNTIME)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.GENRES))) {
                String genres = movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.GENRES));
                ArrayList<String> allGenres = new ArrayList<String>();
                Log.d(TAG, "Genre = " + genres);
                if (genres.contains(",")) {
                    String[] g = genres.split(",");
                    for (String s :
                            g) {
                        allGenres.add(s);
                    }
                } else {
                    allGenres.add(genres.substring(1, genres.length() - 1));
                }
                movieData.setGenres(allGenres);
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SUMMARY))) {
                movieData.setSummary(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SUMMARY)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DESCRIPTION_FULL))) {
                movieData.setDescription_full(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DESCRIPTION_FULL)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SYNOPSIS))) {
                movieData.setSynopsis(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SYNOPSIS)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YT_TRAILER_CODE))) {
                movieData.setYt_trailer_code(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YT_TRAILER_CODE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LANGUAGE))) {
                movieData.setLanguage(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LANGUAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MPA_RATING))) {
                movieData.setMpa_rating(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MPA_RATING)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE))) {
                movieData.setBackground_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE_ORIGINAL))) {
                movieData.setBackground_image_original(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE_ORIGINAL)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SMALL_COVER_IMAGE))) {
                movieData.setSmall_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SMALL_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MEDIUM_COVER_IMAGE))) {
                movieData.setMedium_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MEDIUM_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LARGE_COVER_IMAGE))) {
                movieData.setLarge_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LARGE_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.STATE))) {
                movieData.setState(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.STATE)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENTS))) {
                ArrayList<TorrentData> torrentDataArrayList = new ArrayList<>();
                JSONArray torrentJsonArray = movie.getJSONArray(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENTS));
                int torrents = torrentJsonArray.length();
                for (int j = 0; j < torrents; j++) {
                    JSONObject jsonObject1 = torrentJsonArray.getJSONObject(j);
                    TorrentData torrentData = new TorrentData();

                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_URL))) {
                        torrentData.setUrl(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_URL)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_HASH))) {
                        torrentData.setHash(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_HASH)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_QUALITY))) {
                        torrentData.setQuality(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_QUALITY)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SEEDS))) {
                        torrentData.setSeeds(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SEEDS)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_PEERS))) {
                        torrentData.setPeers(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_PEERS)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE))) {
                        torrentData.setSize(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE_BYTES))) {
                        torrentData.setSize_bytes(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE_BYTES)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED))) {
                        torrentData.setDate_uploaded(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED_UNIX))) {
                        torrentData.setDate_uploaded_unix(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED_UNIX)));
                    }
                    torrentDataArrayList.add(torrentData);
                }

                movieData.setTorrents(torrentDataArrayList);
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED))) {
                movieData.setDate_uploaded(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED_UNIX))) {
                movieData.setDate_uploaded_unix(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED_UNIX)));
            }
            return movieData;
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return null;
    }


    public MovieData createMovieData(JSONObject movie) {
        try {
            MovieData movieData = new MovieData();


            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.ID))) {
                movieData.setId(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.ID)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIE_URL))) {
                movieData.setMovieURL(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MOVIE_URL)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.IMDB_CODE))) {
                movieData.setImdb_code(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.IMDB_CODE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE))) {
                movieData.setTitle(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_ENGLISH))) {
                movieData.setTitle_english(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_ENGLISH)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_LONG))) {
                movieData.setTitle_long(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TITLE_LONG)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SLUG))) {
                movieData.setSlug(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SLUG)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YEAR))) {
                movieData.setYear(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YEAR)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RATING))) {
                movieData.setRating(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RATING)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RUNTIME))) {
                movieData.setRuntime(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.RUNTIME)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.GENRES))) {
                String genres = movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.GENRES));
                ArrayList<String> allGenres = new ArrayList<String>();
                Log.d(TAG, "Genre = " + genres);
                if (genres.contains(",")) {
                    String[] g = genres.split(",");
                    for (String s :
                            g) {
                        allGenres.add(s);
                    }
                } else {
                    allGenres.add(genres.substring(1, genres.length() - 1));
                }
                movieData.setGenres(allGenres);
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SUMMARY))) {
                movieData.setSummary(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SUMMARY)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DESCRIPTION_FULL))) {
                movieData.setDescription_full(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DESCRIPTION_FULL)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SYNOPSIS))) {
                movieData.setSynopsis(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SYNOPSIS)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YT_TRAILER_CODE))) {
                movieData.setYt_trailer_code(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.YT_TRAILER_CODE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LANGUAGE))) {
                movieData.setLanguage(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LANGUAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MPA_RATING))) {
                movieData.setMpa_rating(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MPA_RATING)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE))) {
                movieData.setBackground_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE_ORIGINAL))) {
                movieData.setBackground_image_original(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.BACKGROUND_IMAGE_ORIGINAL)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SMALL_COVER_IMAGE))) {
                movieData.setSmall_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.SMALL_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MEDIUM_COVER_IMAGE))) {
                movieData.setMedium_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.MEDIUM_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LARGE_COVER_IMAGE))) {
                movieData.setLarge_cover_image(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.LARGE_COVER_IMAGE)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.STATE))) {
                movieData.setState(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.STATE)));
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENTS))) {
                ArrayList<TorrentData> torrentDataArrayList = new ArrayList<>();
                JSONArray torrentJsonArray = movie.getJSONArray(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENTS));
                int torrents = torrentJsonArray.length();
                for (int j = 0; j < torrents; j++) {
                    JSONObject jsonObject1 = torrentJsonArray.getJSONObject(j);
                    TorrentData torrentData = new TorrentData();

                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_URL))) {
                        torrentData.setUrl(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_URL)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_HASH))) {
                        torrentData.setHash(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_HASH)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_QUALITY))) {
                        torrentData.setQuality(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_QUALITY)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SEEDS))) {
                        torrentData.setSeeds(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SEEDS)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_PEERS))) {
                        torrentData.setPeers(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_PEERS)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE))) {
                        torrentData.setSize(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE_BYTES))) {
                        torrentData.setSize_bytes(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_SIZE_BYTES)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED))) {
                        torrentData.setDate_uploaded(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED)));

                    }
                    if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED_UNIX))) {
                        torrentData.setDate_uploaded_unix(jsonObject1.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.TORRENT_DATE_UPLOADED_UNIX)));
                    }
                    torrentDataArrayList.add(torrentData);
                }

                movieData.setTorrents(torrentDataArrayList);
            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED))) {
                movieData.setDate_uploaded(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED)));

            }
            if (movie.has(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED_UNIX))) {
                movieData.setDate_uploaded_unix(movie.getString(Helper.getInstance().getTorrentAPIKey(Helper.API_KEY.DATE_UPLOADED_UNIX)));
            }
            return movieData;
        } catch (JSONException jse) {
            jse.printStackTrace();
        }
        return null;
    }

    public String getTime()
    {
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        return date;
    }
}

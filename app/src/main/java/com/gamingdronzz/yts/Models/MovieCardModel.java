package com.gamingdronzz.yts.Models;

/**
 * Created by balpreet on 3/28/2018.
 */

public class MovieCardModel {
    private String MovieTitle,MovieTime,MovieReleaseYear,MovieCoverURL,MovieID;

    public String getMovieTitle() {
        return MovieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        MovieTitle = movieTitle;
    }

    public String getMovieTime() {
        return MovieTime;
    }

    public void setMovieTime(String movieTime) {
        MovieTime = movieTime;
    }

    public String getMovieReleaseYear() {
        return MovieReleaseYear;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        MovieReleaseYear = movieReleaseYear;
    }

    public String getMovieCoverURL() {
        return MovieCoverURL;
    }

    public void setMovieCoverURL(String movieCoverURL) {
        MovieCoverURL = movieCoverURL;
    }

    public String getMovieID() {
        return MovieID;
    }

    public void setMovieID(String movieID) {
        MovieID = movieID;
    }

    public MovieCardModel(String movieTitle, String movieTime, String movieReleaseYear, String movieCoverURL, String movieID) {

        MovieTitle = movieTitle;
        MovieTime = movieTime;
        MovieReleaseYear = movieReleaseYear;
        MovieCoverURL = movieCoverURL;
        MovieID = movieID;
    }

    public MovieCardModel() {

    }
}

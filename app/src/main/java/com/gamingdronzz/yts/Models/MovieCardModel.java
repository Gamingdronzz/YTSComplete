package com.gamingdronzz.yts.Models;

/**
 * Created by balpreet on 3/24/2018.
 */

public class MovieCardModel {

    String imageURL;
    String movieName;
    String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public MovieCardModel() {

    }
}

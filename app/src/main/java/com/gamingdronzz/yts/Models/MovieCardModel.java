package com.gamingdronzz.yts.Models;

/**
 * Created by balpreet on 3/24/2018.
 */

public class MovieCardModel {

    String imageURL;
    String movieName;

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

    public MovieCardModel(String imageURL, String movieName) {

        this.imageURL = imageURL;
        this.movieName = movieName;
    }
}

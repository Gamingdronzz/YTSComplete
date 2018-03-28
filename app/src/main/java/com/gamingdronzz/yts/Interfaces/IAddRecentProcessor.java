package com.gamingdronzz.yts.Interfaces;

import android.content.Context;

import com.gamingdronzz.yts.Models.MovieCardModel;

/**
 * Created by balpreet on 3/28/2018.
 */

public interface IAddRecentProcessor {
    void addRecent(MovieCardModel movieCardModel, Context context);

}

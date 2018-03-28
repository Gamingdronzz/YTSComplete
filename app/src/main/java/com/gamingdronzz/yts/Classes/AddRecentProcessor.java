package com.gamingdronzz.yts.Classes;

import android.content.Context;

import com.gamingdronzz.yts.Interfaces.IAddRecentProcessor;
import com.gamingdronzz.yts.Models.MovieCardModel;
import com.gamingdronzz.yts.Tools.PreferencesManager;

/**
 * Created by balpreet on 3/28/2018.
 */

public class AddRecentProcessor implements IAddRecentProcessor {
    @Override
    public void addRecent(MovieCardModel movieCardModel, Context context) {
        PreferencesManager preferencesManager = new PreferencesManager(context);
        preferencesManager.addRecent(movieCardModel);
    }
}

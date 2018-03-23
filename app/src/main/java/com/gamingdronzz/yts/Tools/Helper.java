package com.gamingdronzz.yts.Tools;

/**
 * Created by balpreet on 3/22/2018.
 */

public class Helper {

    private static Helper _instance;

    private Helper() {
    }

    ;

    public static Helper getInstance() {
        if (_instance == null) {
            _instance = new Helper();
        }
        return _instance;
    }

    public String getBaseURL() {
        return "https://yts.am/api/v2/";
    }
}

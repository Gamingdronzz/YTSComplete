package com.gamingdronzz.yts.Classes;

import android.os.AsyncTask;
import android.util.Log;

import com.gamingdronzz.yts.Interfaces.IURLProcessor;
import com.gamingdronzz.yts.Listeners.OnURLAvailabilityCheckedListener;
import com.gamingdronzz.yts.Tools.Helper;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by balpreet on 3/23/2018.
 */

public class URLProcessor implements IURLProcessor {
    private OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener;


    final String TAG = "URL Processor";

//    @Override
//    public void setOnURLAvailabilityCheckedListener(OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener) {
//        this.onURLAvailabilityCheckedListener = onURLAvailabilityCheckedListener;
//    }

    public URLProcessor(OnURLAvailabilityCheckedListener onURLAvailabilityCheckedListener)
    {
        this.onURLAvailabilityCheckedListener = onURLAvailabilityCheckedListener;
    }

    @Override
    public void CheckURLAvailability() {
        String customURL = Helper.getInstance().getBaseURL();
        MyTask task = new MyTask();
        task.execute(customURL);
    }

    private class MyTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {
                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection con =  (HttpURLConnection) new URL(params[0]).openConnection();
                con.setRequestMethod("HEAD");
                System.out.println(con.getResponseCode());
                return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            boolean bResponse = result;
            if (bResponse==true)
            {
                Log.d(TAG,"URl Exists");
                if (onURLAvailabilityCheckedListener != null) {
                    onURLAvailabilityCheckedListener.OnUrlAvailable();
                }
            }
            else
            {
                Log.d(TAG,"URl Does not Exist");
                if (onURLAvailabilityCheckedListener != null) {
                    onURLAvailabilityCheckedListener.OnUrlNotAvailable();
                }
            }
        }
    }
}

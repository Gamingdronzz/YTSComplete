package com.gamingdronzz.yts.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.gamingdronzz.yts.R;
import com.gamingdronzz.yts.Tools.VolleyHelper;

public class MainActivity extends AppCompatActivity implements VolleyHelper.VolleyResponse {

    VolleyHelper volleyHelper;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volleyHelper = new VolleyHelper(this,this);
        volleyHelper.makeStringRequest("https://yts.am/api/v2/list_movies.json?quality=3D","3D");
    }

    @Override
    public void onError(VolleyError volleyError) {

    }

    @Override
    public void onResponse(String str) {
        Log.d("Main","Resposne = " + str);
    }
}

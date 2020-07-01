package com.gmail.kaminskysem.PersnalHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class PlanerActivity extends AppCompatActivity {
   private static String LOG_TAG = PlanerActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer);

        Log.d(LOG_TAG, "onCreate"+this);
    }

    // TODO: 15.06.2020   передалать список в recycleView


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart"+this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume"+this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause"+this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop"+this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "onDestroy"+this);

    }
}
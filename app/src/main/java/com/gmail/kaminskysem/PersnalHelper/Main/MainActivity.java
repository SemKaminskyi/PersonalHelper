package com.gmail.kaminskysem.PersnalHelper.Main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.PlanerActivity;

public class MainActivity extends AppCompatActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private Button btnTimer;
    private Button btnPlaner;

//TODO add orientation to mode 2 apps in one screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener btnOnClickPlaner =new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnOnClickPlaner"+v);
                Intent mainActPlaner = new Intent(MainActivity.this, PlanerActivity.class);
                startActivity(mainActPlaner);
                Toast.makeText(MainActivity.this,"You click on Planer", Toast.LENGTH_SHORT).show();
            }
        };
        View.OnClickListener btnOnClickTimer = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "btnOnClickTimer"+v);
                Intent mainActTimer = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(mainActTimer);
                Toast.makeText(MainActivity.this,"You click on Timer", Toast.LENGTH_LONG).show();
            }
        };



        btnPlaner = findViewById(R.id.btn_planer);
        btnPlaner.setOnClickListener(btnOnClickPlaner);

        btnTimer =findViewById(R.id.btn_clockwork);
        btnTimer.setOnClickListener(btnOnClickTimer);

        Log.d(LOG_TAG, "OnCreate, "+this);

        FragmentMainScrollingImages fragmentMainScrollingImages = new FragmentMainScrollingImages();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_image_container,fragmentMainScrollingImages)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart, "+this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume, "+this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause, "+this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop, "+this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy, "+this);
    }
}
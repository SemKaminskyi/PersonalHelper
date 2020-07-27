package com.gmail.kaminskysem.PersnalHelper.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planer.PlanerActivity;

public class MainActivity extends AppCompatActivity {
    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private Button btnTimer;
    private Button btnPlaner;

        View.OnClickListener btnOnClickPlaner =new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnOnClickPlaner"+v);
                Intent mainActPlaner = new Intent(MainActivity.this, PlanerActivity.class);
                startActivity(mainActPlaner);
                Toast.makeText(MainActivity.this,"You click on Planer", Toast.LENGTH_LONG).show();
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
    // TODO: 16.06.2020 cheaking intent file and replace code of intent in MAnifest
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(LOG_TAG, "OnCreate, "+this);

        btnPlaner = findViewById(R.id.btn_planer);
        btnPlaner.setOnClickListener(btnOnClickPlaner);

        btnTimer =findViewById(R.id.btn_clockwork);
        btnTimer.setOnClickListener(btnOnClickTimer);


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
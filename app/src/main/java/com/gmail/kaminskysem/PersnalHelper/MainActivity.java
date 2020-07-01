package com.gmail.kaminskysem.PersnalHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity2 extends AppCompatActivity {
    private static String LOG_TAG = MainActivity2.class.getSimpleName();
    private Button btnClockwork;

        View.OnClickListener btnOnClickClock =new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG,"btnOnClickClock"+v);
                Intent mainAct = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(mainAct);
            }
        };
    // TODO: 16.06.2020 cheaking intent file and replace code of intent in MAnifest
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Log.d(LOG_TAG, "OnCreate, "+this);
        btnClockwork = findViewById(R.id.btn_clockwork);
        btnClockwork.setOnClickListener(btnOnClickClock);

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
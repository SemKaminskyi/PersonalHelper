package com.gmail.kaminskysem.PersnalHelper.Main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bcgdv.asia.lib.ticktock.BuildConfig;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.TimerActivity;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.PlanerActivity;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {


//TODO add orientation to mode 2 apps in one screen


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener btnOnClickPlaner = v -> {
            if (BuildConfig.DEBUG) {
                Timber.d("btnOnClickPlaner%s", v);
            }
            Intent mainActPlaner = new Intent(MainActivity.this, PlanerActivity.class);
            startActivity(mainActPlaner);
            Toast.makeText(MainActivity.this, "You click on Planer", Toast.LENGTH_SHORT).show();
        };
        View.OnClickListener btnOnClickTimer = v -> {
            if (BuildConfig.DEBUG) {

                Timber.d("btnOnClickTimer%s", v);
            }
            Intent mainActTimer = new Intent(MainActivity.this, TimerActivity.class);
            startActivity(mainActTimer);
            Toast.makeText(MainActivity.this, "You click on Timer", Toast.LENGTH_LONG).show();
        };


        Button btnPlaner = findViewById(R.id.btn_planer);
        btnPlaner.setOnClickListener(btnOnClickPlaner);

        Button btnTimer = findViewById(R.id.btn_clockwork);
        btnTimer.setOnClickListener(btnOnClickTimer);

        Timber.d("OnCreate, %s", this);

        FragmentMainScrollingImages fragmentMainScrollingImages = new FragmentMainScrollingImages();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_image_container, fragmentMainScrollingImages)
                .commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {

            Timber.d("onStart, %s", this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // add crash Test
//        Button crashButton = new Button(this);
//        crashButton.setText("Crash!");
//        crashButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                throw new RuntimeException("Test Crash"); // Force a crash
//            }
//        });
//
//        addContentView(crashButton, new ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));


        Timber.d("onResume, %s", this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause, %s", this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop, %s", this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy, %s", this);
    }
}
package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmail.kaminskysem.PersnalHelper.Main.FragmentMainScrollingImages;
import com.gmail.kaminskysem.PersnalHelper.R;

public class TimerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        //add timer fragment
        TimerTImerFragment timerFragment = new TimerTImerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_timer_container, timerFragment)
                .commit();

        // add fragment from main activity
        FragmentMainScrollingImages fragmentMainScrollingImages = new FragmentMainScrollingImages();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main_image_container,fragmentMainScrollingImages)
                .commit();


    }
}

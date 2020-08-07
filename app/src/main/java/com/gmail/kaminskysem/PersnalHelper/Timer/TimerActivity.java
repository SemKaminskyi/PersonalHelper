package com.gmail.kaminskysem.PersnalHelper.Timer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gmail.kaminskysem.PersnalHelper.R;

public class TimerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        TimerTImerFragment timerFragment = new TimerTImerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_timer_container, timerFragment)
                .commit();

    }
}
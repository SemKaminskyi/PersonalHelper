package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;

import java.util.Objects;

public class TimerTImerFragment extends Fragment {
    EditText etWork;
    EditText etRest;
    Button bntStart;
    Button bntStop;
    TextView textView;
    private String stringWorkTimer;
    private String stringRestTimer;

    private MediaPlayer mediaPlayer;



    private static String LOG_TAG = TimerTImerFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView" + container);
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etWork = Objects.requireNonNull(getView()).findViewById(R.id.et_Timer_time_to_work);
        etRest =  Objects.requireNonNull(getView().findViewById(R.id.et_Timer_time_to_rest));

        bntStart = getView().findViewById(R.id.btn_timer_start);


        textView = getView().findViewById(R.id.tv_Timer);


        bntStop = getView().findViewById(R.id.btn_timer_stop);
        bntStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Service  is stopped from fragment " );

                Intent intentStop = new Intent(Objects.requireNonNull(getView()).getContext(), TimerService.class)
                        .setAction(TimerService.ACTION_STOP_TIMER);

                getView().getContext().startService(intentStop);

            }
        });

        bntStart.setOnClickListener(v -> {
            stringWorkTimer = etWork.getText().toString();
            stringRestTimer = etRest.getText().toString();
            Log.d(LOG_TAG, "TimerWorkFragment is " + stringWorkTimer);
            Log.d(LOG_TAG, "TimerRestFragment is " + stringRestTimer);
            Intent intentStart = new Intent(getView().getContext(), TimerService.class)
                    .putExtra("TimerWork", stringWorkTimer)
                    .putExtra("TimerRest", stringRestTimer)
                    .setAction(TimerService.ACTION_START_TIMER);

            mediaPlayer = MediaPlayer.create(getView().getContext(), R.raw.ticking_clock);
            mediaPlayer.start();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getView().getContext().startForegroundService(intentStart);
            }else
            Log.d(LOG_TAG, "bntStart ON Clicked " + v);
        });
    }



    @Override
    public void onDestroyView() {
        mediaPlayer.stop();
        super.onDestroyView();
    }
}
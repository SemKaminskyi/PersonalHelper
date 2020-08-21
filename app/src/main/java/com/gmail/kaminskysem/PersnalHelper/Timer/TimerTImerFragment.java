package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerBinder;
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;

import java.util.Objects;

public class TimerTImerFragment extends Fragment {
    private final static String LOG_TAG = TimerTImerFragment.class.getSimpleName();

    EditText etWork;
    EditText etRest;
    Button bntStart;
    Button bntStop;
    TextView textViewTimer;
    private String stringWorkTimer;
    private String stringRestTimer;

    private MediaPlayer mediaPlayer;


    private static ServiceConnection serviceConnection;
    private boolean bound = false;
    Class<TimerService> timerService;


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
        etRest = Objects.requireNonNull(getView().findViewById(R.id.et_Timer_time_to_rest));
        textViewTimer = getView().findViewById(R.id.tv_Timer);

        bntStart = getView().findViewById(R.id.btn_timer_start);

        bntStop = getView().findViewById(R.id.btn_timer_stop);


        //Btn START onClIck
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

            //connection to service
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    
                    //TODO   в примере идет каст -  у меня просит статические методы и т.д
                    timerService = TimerBinder.getService();

                    bound = true;
                    Log.d(LOG_TAG, "TimerFragment connected" +service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    bound = false;
                    Log.d(LOG_TAG, "TimerFragment Disconnected");
                }
            };
            getView().getContext().bindService(intentStart, serviceConnection, TimerService.BIND_AUTO_CREATE);
            getView().getContext().startService(intentStart);


            //add text to textView
            if (TimerService.getCountDownTimerWork() != null) {
                textViewTimer.setText(TimerService.getTimeWorkString());
                Log.d(LOG_TAG, "add in TV - work timer ");

            }
            if (TimerService.getCountDownTimeRest() != null) {
                textViewTimer.setText(TimerService.getTimeRestString());
                Log.d(LOG_TAG, "add in TV - rest timer ");

            }

            Log.d(LOG_TAG, "bntStart ON Clicked " + v);

            Log.d(LOG_TAG, "text to fragment work " + TimerService.getTimeWorkString());
            Log.d(LOG_TAG, "text to fragment rest " + TimerService.getTimeRestString());


        });

        //Btn STOP onClIck
        bntStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // unbind service
                if (!bound) return;
                Objects.requireNonNull(getView()).getContext().unbindService(serviceConnection);
                Log.d(LOG_TAG, "TimerFragment UNBIND from bnt STOP");
                bound = false;

                Intent intentStop = new Intent(Objects.requireNonNull(getView()).getContext(), TimerService.class)
                        .setAction(TimerService.ACTION_STOP_TIMER);
                // STOP service
                getView().getContext().startService(intentStop);
                Log.d(LOG_TAG, "Service  is stopped from fragment ");

            }
        });
    }


    @Override
    public void onResume() {
//        if(bound==false){
//
//        }

        super.onResume();
    }

    @Override
    public void onDestroyView() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        super.onDestroyView();
    }

    public static ServiceConnection getServiceConnection() {
        return serviceConnection;
    }
}
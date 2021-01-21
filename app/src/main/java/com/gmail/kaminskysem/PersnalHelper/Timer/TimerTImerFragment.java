package com.gmail.kaminskysem.PersnalHelper.Timer;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Observable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.bcgdv.asia.lib.ticktock.TickTockView;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;

import java.util.Calendar;
import java.util.Objects;

public class TimerTImerFragment extends Fragment {
    private final static String LOG_TAG = TimerTImerFragment.class.getSimpleName();
    public static final String TIMER_WORK = "TimerWork";
    public static final String TIMER_REST = "TimerRest";
    public static final String TIME_FOR_CIRCLE_WORK = "TimerWorkCircle";
    public static final String TIME_FOR_CIRCLE_REST = "TimerRestCircle";


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

    TimerService timerService;
    BroadcastReceiver TimerReceiver;

    public static final String BROADCAST_ACTION = " com.gmail.kaminskysem.PersnalHelper.Timer";
    private TickTockView mCountDown;
    private String timeFromBroadcastWorkToTV;

    // variable to widget
    private String workFromBroadcastStr;
    private String restFromBroadcastStr;
    int iEnd = -1;
    private IntentFilter intentFilter;
    private Calendar start;
    private Calendar end;

    //variable to activate bntStart
    private boolean changeETWork= false;
    private boolean changeETRest= false;
    private TextWatcher textWatcher ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView" + container);
        // create broadcastReceiver
        TimerReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                timeFromBroadcastWorkToTV = intent.getStringExtra(TimerTImerFragment.TIMER_WORK);
                textViewTimer.setText(timeFromBroadcastWorkToTV);
                //receive massage from TimerService time Work
                workFromBroadcastStr = intent.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_WORK);
                if (workFromBroadcastStr != null) {
                    stopWidget();
                    iEnd = Integer.parseInt(workFromBroadcastStr);
                    startWidget();
                    restFromBroadcastStr = null;
                }

                //receive massage from TimerService time REST
                restFromBroadcastStr = intent.getStringExtra(TimerTImerFragment.TIME_FOR_CIRCLE_REST);

                if (restFromBroadcastStr != null) {
                    stopWidget();
                    iEnd = Integer.parseInt(restFromBroadcastStr);
                    end.add(Calendar.MINUTE, iEnd);
                    startWidget();
                    workFromBroadcastStr = null;
                }

                Log.i(LOG_TAG, " broadcast caught: " + timeFromBroadcastWorkToTV);
            }

        };

        // register receiver
        intentFilter = new IntentFilter(TimerTImerFragment.BROADCAST_ACTION);
        Objects.requireNonNull(getActivity()).registerReceiver(TimerReceiver, intentFilter);
        Log.d(LOG_TAG, "register Receiver " + TimerReceiver);
        Log.d(LOG_TAG, "Wregister Receiver " + workFromBroadcastStr);
        Log.d(LOG_TAG, "Rregister Receiver " + restFromBroadcastStr);

        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //init EDIT TExt
        etWork = Objects.requireNonNull(getView()).findViewById(R.id.et_Timer_time_to_work);
        etRest = Objects.requireNonNull(getView().findViewById(R.id.et_Timer_time_to_rest));
        //init textview
        textViewTimer = getView().findViewById(R.id.tv_Timer);
        //init button
        bntStart = getView().findViewById(R.id.btn_timer_start);
        bntStop = getView().findViewById(R.id.btn_timer_stop);

        mediaPlayer = MediaPlayer.create(getView().getContext(), R.raw.ticking_clock);

        //add calendar for widget
        start = Calendar.getInstance();
        start.add(Calendar.MINUTE, 0);
        end = Calendar.getInstance();

//        etWork.setText("0");
//        etRest.setText("0");

        //add timer widget
        mCountDown = new TickTockView(Objects.requireNonNull(getContext()));
        mCountDown = (TickTockView) getView().findViewById(R.id.view_ticktock_countdown);
        addWidget();
        bntStart.setEnabled(false);


        //Btn START onClIck
        bntStart.setOnClickListener(v -> {
            stringWorkTimer = etWork.getText().toString();
            stringRestTimer = etRest.getText().toString();

            Log.d(LOG_TAG, "TimerWorkFragment is " + stringWorkTimer);
            Log.d(LOG_TAG, "TimerRestFragment is " + stringRestTimer);


            //add time to service
            Intent intentStart = new Intent(getView().getContext(), TimerService.class)
                    .putExtra(TIMER_WORK, stringWorkTimer)
                    .putExtra(TIMER_REST, stringRestTimer)
                    .setAction(TimerService.ACTION_START_TIMER);

            getView().getContext().startService(intentStart);

            mediaPlayer.start();

            //connection to service
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                    timerService = ((TimerService.MyBinder) service).getService();
                    bound = true;
                    Log.d(LOG_TAG, "TimerFragment connected" + service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    bound = false;
                    Log.d(LOG_TAG, "TimerFragment Disconnected");
                }
            };
            Objects.requireNonNull(getView()).getContext()
                    .bindService(intentStart,
                            serviceConnection,
                            TimerService.BIND_AUTO_CREATE);


            Log.d(LOG_TAG, "bntStart ON Clicked " + v);

            Log.d(LOG_TAG, "text to fragment work ");
            Log.d(LOG_TAG, "text to fragment rest ");


        });

        //Btn STOP onClIck
        bntStop.setOnClickListener(v -> {
            // unbind service
//            if (!bound) return;
//            Objects.requireNonNull(getView()).getContext().unbindService(serviceConnection);
//            Log.d(LOG_TAG, "TimerFragment UNBIND from bnt STOP");
//            bound = false;

            Intent intentStop = new Intent(Objects.requireNonNull(getView()).getContext(), TimerService.class)
                    .setAction(TimerService.ACTION_STOP_TIMER);
            // STOP service
            getView().getContext().startService(intentStop);

            Log.d(LOG_TAG, "Service  is stopped from fragment ");

            stopWidget();
        });
    }

    private void addWidget() {

        if (mCountDown != null) {
            ((TickTockView) mCountDown).setOnTickListener(new TickTockView.OnTickListener() {
                @Override
                public String getText(long timeRemaining) {
                    int seconds = (int) (timeRemaining / 1000) % 60;
                    int minutes = (int) ((timeRemaining / (1000 * 60)) % 60);
                    int hours = (int) ((timeRemaining / (1000 * 60 * 60)) % 24);
                    int days = (int) (timeRemaining / (1000 * 60 * 60 * 24));
                    boolean hasDays = days > 0;
                    return String.format("%1$02d%4$s %2$02d%5$s %3$02d%6$s",
                            hasDays ? days : hours,
                            hasDays ? hours : minutes,
                            hasDays ? minutes : seconds,
                            hasDays ? "d" : "h",
                            hasDays ? "h" : "m",
                            hasDays ? "m" : "s");
                }
            });
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        checkOnChangeETWork();
        checkOnChangeETRest();

    }

    private void checkOnChangeETWork() {
//        if(textWatcher!=null){
//            etRest.removeTextChangedListener(textWatcher);
//        }
       etWork.addTextChangedListener(textWatcher = new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(etWork.getText().toString().length()>0&&etRest.getText().toString().length()>0){
                   bntStart.setEnabled(true);
               } else {
                   bntStart.setEnabled(false);
               }
           }

           @Override
           public void afterTextChanged(Editable s) { }
       });


    }
    private void checkOnChangeETRest() {
//        if(textWatcher!=null){
//            etWork.removeTextChangedListener(textWatcher);
//        }
        etRest.addTextChangedListener(textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(etWork.getText().toString().length()>0&&etRest.getText().toString().length()>0){
                    bntStart.setEnabled(true);
                } else {
                    bntStart.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        stopWidget();
        Objects.requireNonNull(getView()).getContext().unregisterReceiver(TimerReceiver);
    }

    //    public class NonTwoTimeExceptions extends RuntimeException {
//        public NonTwoTimeExceptions(String errorMessage) {
//            super(errorMessage);
//        }
//    }


    private void stopWidget() {
        if (mCountDown != null) {
            mCountDown.refreshDrawableState();
            mCountDown.stop();

        }
    }

    private void startWidget() {
        if (iEnd >= 0 && end != null) {
            start = Calendar.getInstance();
            start.add(Calendar.MINUTE, 0);
            end = Calendar.getInstance();
            end.add(Calendar.MINUTE, iEnd);
            start.add(Calendar.MINUTE, 0);
            if (mCountDown != null) {
                mCountDown.start(start, end);
            }
        }

    }
}
package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    public static final String TIMER_WORK = "TimerWork";
    public static final String TIMER_REST = "TimerRest";

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
    //TODO change
    Class<TimerService> timerService;
//TimerService timerService;

    //    BroadcastReceiver br;
    TimerReceiver timerReceiver;
    public static final String PARAM_TIME = "status";
    public static final String BROADCAST_ACTION = " com.gmail.kaminskysem.PersnalHelper.Timer";
    private CountDownTimer timer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView" + container);
        // create broadcastReceiver
        timerReceiver = new TimerReceiver();

        //register receiver
        IntentFilter intentFilter = new IntentFilter(TimerReceiver.SIMPLE_ACTION);
        getActivity().registerReceiver(timerReceiver, intentFilter);
        Log.d(LOG_TAG, "register Receiver " + timerReceiver);

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
//        br = new BroadcastReceiver() {
//            @SuppressLint({"SetTextI18n", "ShowToast"})
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                    String timeFromBroadcastWork = intent.getAction();
////                    String timeFromBroadcastRest = intent.getStringExtra(TIMER_REST);
//                Toast.makeText(Objects.requireNonNull(getView()).getContext(),intent.getAction(), Toast.LENGTH_SHORT);
//                Log.d(LOG_TAG, "onReceive: time, work: "+ timeFromBroadcastWork);
//

////                    if(timeFromBroadcastRest!=null){
////                        textViewTimer.setText("time: "+ timeFromBroadcastRest);
////                    }
//            }
//        };


        // add filter to BroadcastReceiver
//        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
//        getView().getContext().registerReceiver(br,intentFilter);




//        if(TimerReceiver.timeFromBroadcastWork!=null){
            textViewTimer.setText("time: "+ TimerReceiver.timeFromBroadcastWork);

//        }

        //Btn START onClIck
        bntStart.setOnClickListener(v -> {
            stringWorkTimer = etWork.getText().toString();
            stringRestTimer = etRest.getText().toString();
            Log.d(LOG_TAG, "TimerWorkFragment is " + stringWorkTimer);
            Log.d(LOG_TAG, "TimerRestFragment is " + stringRestTimer);
        cicleUpdateTV();

            Intent intentStart = new Intent(getView().getContext(), TimerService.class)
                    .putExtra(TIMER_WORK, stringWorkTimer)
                    .putExtra(TIMER_REST, stringRestTimer)
                    .setAction(TimerService.ACTION_START_TIMER);

            getView().getContext().startService(intentStart);

            mediaPlayer = MediaPlayer.create(getView().getContext(), R.raw.ticking_clock);
            mediaPlayer.start();

            //connection to service
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {

                    //TODO   в примере идет каст -  у меня просит статические методы и т.д
                    // у автора было
                    // myService = ((MyService.MyBinder) binder).getService();
//                    timerService =((TimerService.MyBinder)timerService.binder).getService();
                    timerService = TimerBinder.getService();
                    bound = true;
                    Log.d(LOG_TAG, "TimerFragment connected" + service);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    bound = false;
                    Log.d(LOG_TAG, "TimerFragment Disconnected");
                }
            };
            getView().getContext().bindService(intentStart, serviceConnection, TimerService.BIND_AUTO_CREATE);


            //add text to textView
//            if (TimerService.getCountDownTimerWork() != null) {
//                textViewTimer.setText(timerService.getTimeWorkString());
//                Log.d(LOG_TAG, "add in TV - work timer ");

//            }
//            if (TimerService.getCountDownTimeRest() != null) {
//                textViewTimer.setText(timerService.getTimeRestString());
//                Log.d(LOG_TAG, "add in TV - rest timer ");
//
//            }

            Log.d(LOG_TAG, "bntStart ON Clicked " + v);

            Log.d(LOG_TAG, "text to fragment work ");
            Log.d(LOG_TAG, "text to fragment rest ");

            getView().getContext().sendBroadcast(new Intent(TimerReceiver.SIMPLE_ACTION));
        });

        //Btn STOP onClIck
        bntStop.setOnClickListener(v -> {
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
            timer.cancel();

        });
    }

    private void cicleUpdateTV() {
        Long ada = Long.parseLong(stringWorkTimer)*1000;
        timer = new CountDownTimer(ada, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText("time: "+ TimerReceiver.timeFromBroadcastWork);
                Log.i(LOG_TAG, "update TV "+textViewTimer.getText());

            }

            @Override
            public void onFinish() {
                cicleUpdateTV();
            }

        }.start();
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
        super.onDestroyView();
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();
        Objects.requireNonNull(getView()).getContext().unregisterReceiver(timerReceiver);
    }

    public static ServiceConnection getServiceConnection() {
        return serviceConnection;
    }
}
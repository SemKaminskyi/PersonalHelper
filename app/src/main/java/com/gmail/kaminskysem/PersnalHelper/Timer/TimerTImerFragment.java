package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
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
import com.gmail.kaminskysem.PersnalHelper.Timer.Service.TimerService;

public class TimerTImerFragment extends Fragment {
    EditText etWork;
    EditText etRest;
    Button bntStart;
    Button bntStop;
    TextView textView;
    private String stringWorkTimer;
    private String stringRestTimer;
    private Intent intent;


    public Button getBntStart() {
        return bntStart;
    }


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

        etWork = getView().findViewById(R.id.et_Timer_time_to_work);
        etRest = getView().findViewById(R.id.et_Timer_time_to_rest);

        bntStart = getView().findViewById(R.id.btn_timer_start);


        textView = getView().findViewById(R.id.tv_Timer);

        // TODO working timer in new Thread and background process
        boolean timerOn = true;

        bntStop = getView().findViewById(R.id.btn_timer_stop);
//        bntStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//        });
        stringWorkTimer = etWork.getText().toString();
        stringRestTimer = etRest.getText().toString();

            bntStart.setOnClickListener(v -> {
                intent = new Intent(getView().getContext(), TimerService.class);
                getView().getContext().startService(intent.putExtra("TimerWork", stringWorkTimer).putExtra("TimerRest", stringRestTimer).setAction("timeOn"));


            Log.d(LOG_TAG, "bntStart ON CLICked "+v);
        });
    }

    public void workTimer() {
        if (!TextUtils.isEmpty(stringWorkTimer)) {
            long secondsWork = Long.parseLong(stringWorkTimer);

        }
    }


    private void rest() {
        if (!TextUtils.isEmpty(stringRestTimer)) {

            long secondsWork = Long.parseLong(stringRestTimer);

        }
    }


    public EditText getEtRest() {
        return etRest;
    }

    public EditText getEtWork() {
        return etWork;
    }
}
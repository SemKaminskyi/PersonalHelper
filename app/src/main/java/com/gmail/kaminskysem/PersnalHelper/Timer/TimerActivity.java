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

    EditText etWork;
    EditText etRest;
    Button bntStart;
    Button bntStop;
        TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        TimerFragment timerFragment = new TimerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.)

        etWork = (EditText) findViewById(R.id.et_Timer_time_to_work);
        etRest = (EditText) findViewById(R.id.et_Timer_time_to_rest);

        bntStart = (Button) findViewById(R.id.btn_timer_start);


        textView = (TextView) findViewById(R.id.tv_Timer);
// TODO working timer in new Thread and background process
        boolean timerOn = true;
            startWork();
        bntStop = (Button) findViewById(R.id.btn_timer_stop);
//        bntStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//        });

    }

    private void startWork() {
        bntStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = etWork.getText().toString();
                if (!string.equalsIgnoreCase("")) {

                    long secondsWork = Long.parseLong(string);
                    CountDownTimer countDownTimer = new CountDownTimer(secondsWork * 1000, 1000) {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTick(long millisUntilFinished) {
                            textView.setText("seconds of  Work" + (int) (millisUntilFinished / 1000));
                        }

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onFinish() {
                            textView.setText("Finished");
                            rest();
                        }
                    }.start();
                }
            }
        });
    }

    private void rest() {
        String string = etRest.getText().toString();
        if (!string.equalsIgnoreCase("")) {

            long secondsWork = Long.parseLong(string);
            CountDownTimer countDownTimer = new CountDownTimer(secondsWork * 1000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    textView.setText("seconds of  Rest" + (int) (millisUntilFinished / 1000));
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onFinish() {
                    textView.setText("Finished");

                }
            }.start();
        }
    }
}

package com.gmail.kaminskysem.PersnalHelper.planer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.Data.InMemoryPlanerProvider;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetails;
import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetailsAdapter;

import java.util.List;

public final class PlanerActivity extends AppCompatActivity {
    private static String LOG_TAG = PlanerActivity.class.getSimpleName();
    private List<PlanerDetails> tasksList;
    private RecyclerView rvPlanerTask;
    private PlanerDetailsAdapter adapter;
    private Button btnAddTask;
    private EditText etNewTask;
    private String newTaskFromUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer);

        adapter = new PlanerDetailsAdapter();
        rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));

//        List<PlanerDetails> planerList = null;// была ошибка в planerList - идея предложила создать поле
        tasksList = new InMemoryPlanerProvider().getPlanerDetails();
        adapter.setPlaner(tasksList);

// строки 46-66 нужно подчистить написана белиберда, но єта белиберда стала передавать текст в новую задачу

        //TODO доделать сохранение на диск (dataBase) -  пока не работатет 12.07.2020 00:15
        btnAddTask = (Button) findViewById(R.id.btn_add_task);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanerDetails newTask = new PlanerDetails(-1, newTaskFromUser, false);
                tasksList.add(0, newTask);
                adapter.setPlaner(tasksList);
                Log.d(LOG_TAG, "onClickBTNAdd" + this);

                etNewTask = (EditText) findViewById(R.id.et_new_task);
                if (etNewTask != null) {

                    etNewTask.getShowSoftInputOnFocus();
                    etNewTask.setSelected(true);
                    newTaskFromUser = etNewTask.getText().toString();
                    Log.d(LOG_TAG, " READ TEXT FROM EDIT TEXT IS: " + newTaskFromUser);

                    newTask.setTask(newTaskFromUser);
                    Log.d(LOG_TAG, " added TEXT FROM EDIT TEXT IS: " + newTaskFromUser+ "new task: " +newTask.toString());
                }
            }
        });

        Log.d(LOG_TAG, "onCreate" + this);
    }

    // TODO: 15.06.2020   передалать список в recycleView


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart" + this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //  не знаю нужен ли этот код но хотел добавить открытие клавиатуры и редактирование едит текста.. пока не вышло
        //TODO  добавить автокликера для едит текст


        Log.d(LOG_TAG, "onResume" + this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause" + this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop" + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "onDestroy" + this);

    }
}
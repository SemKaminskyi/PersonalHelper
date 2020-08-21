package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

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
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetailsAdapter;

import java.util.List;

public final class PlanerActivity extends AppCompatActivity {
    private static String LOG_TAG = PlanerActivity.class.getSimpleName();
    private List<PlanerDetails> tasksList;
    private RecyclerView rvPlanerTask;
    private PlanerDetailsAdapter adapter;
    private Button btnAddTask;
    private EditText etNewTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer_todo_list);

        adapter = new PlanerDetailsAdapter();
        rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));

        // нужный ли метод вызвал?
        tasksList = new InMemoryPlanerProvider().getPlanerDetails();
        adapter.setPlaner(tasksList);

// строки 46-66 нужно подчистить написана белиберда, но єта белиберда стала передавать текст в новую задачу

        //TODO доделать сохранение на диск (dataBase) -  пока не работатет 12.07.2020 00:15
        btnAddTask = (Button) findViewById(R.id.btn_add_task);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PlanerDetails newTask = new PlanerDetails(-1, "", false);
//                tasksList.add(0, newTask);

                adapter.setPlaner(tasksList);
                Log.d(LOG_TAG, "onClickBTNAdd" + this);


            }
        });

        Log.d(LOG_TAG, "onCreate" + this);
    }


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
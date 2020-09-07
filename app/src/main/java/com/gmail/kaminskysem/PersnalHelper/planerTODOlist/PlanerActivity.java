package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.Data.InMemoryUserPlanerProvider;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetailsAdapter;

import java.util.List;

public final class PlanerActivity extends AppCompatActivity {
    public static final String ARG_TASK_ID = "arg_task_id";
    private static String LOG_TAG = PlanerActivity.class.getSimpleName();
    private List<PlanerDetails> tasksList;
    private PlanerDetailsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer_todo_list);

        tasksList = new InMemoryUserPlanerProvider().getPlanerDetails();
        adapter = new PlanerDetailsAdapter();

        RecyclerView rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));


        Button btnAddTask = (Button) findViewById(R.id.btn_add_task);

        // click to btn add
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InMemoryUserPlanerProvider.getInstance().addNewTask(new PlanerDetails());
                tasksList.add(0, new PlanerDetails());
                adapter.setPlaner(tasksList);
                Log.d(LOG_TAG, "onClickBTNAdd" + this);
            }
        });

        Log.d(LOG_TAG, "onCreate" + this);
        Log.i(LOG_TAG, tasksList.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart" + this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText etNewTask = findViewById(R.id.et_new_task);

        //TODO  добавить автокликера для едит текст

        // on click item on recyclerView
        adapter.setItemClickListener(new PlanerDetailsItemListener() {
            @Override
            public void onTaskItemClick(int itemId, PlanerDetails planerDetails) {

                InMemoryUserPlanerProvider.getInstance().updateTaskWithID(itemId, planerDetails);
                adapter.notifyDataSetChanged();
            }
        });


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
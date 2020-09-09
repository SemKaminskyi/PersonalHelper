package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data.InMemoryUserPlanerProvider;
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

        adapter = new PlanerDetailsAdapter();

        RecyclerView rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));

        tasksList = new InMemoryUserPlanerProvider().getPlanerDetails();
        adapter.setPlaner(tasksList);

        Button btnAddTask = (Button) findViewById(R.id.btn_add_task);

        // click to btn add
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksList.add(0, new PlanerDetails(5,"",false));
                adapter.setPlaner(tasksList);
                Log.d(LOG_TAG, "onClickBTNAdd" + this);
                Log.d(LOG_TAG, tasksList.toString());
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
//        EditText etNewTask = findViewById(R.id.et_new_task);

        //TODO  добавить автокликера для едит текст

        // on click item on recyclerView
//        adapter.setItemClickListener(new PlanerDetailsItemListener() {
//            @Override
//            public void onTaskItemClick(int itemId, PlanerDetails planerDetails) {
//                InMemoryUserPlanerProvider.getInstance().updateTaskWithID(itemId, planerDetails);
//                adapter.notifyDataSetChanged();
//            }
//        });

        Log.d(LOG_TAG, "onResume" + this);
    }



}
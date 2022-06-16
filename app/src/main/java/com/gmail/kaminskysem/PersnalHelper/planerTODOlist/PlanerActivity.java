package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.MyApp;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetails;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model.PlanerDetailsAdapter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class PlanerActivity extends AppCompatActivity {
    private List<PlanerDetails> tasksList;
    private PlanerDetailsAdapter adapter;

    @Inject
    public IUserPlanerDao userPlanerDaoProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer_todo_list);
        MyApp.getApplicationsComponent(this).inject(this);
        adapter = new PlanerDetailsAdapter(this);

        RecyclerView rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));

        new Thread(() -> {
            tasksList = userPlanerDaoProvider.getTaskList();
            runOnUiThread(() -> adapter.setPlaner(tasksList));
        }).start();

        Button btnAddTask = findViewById(R.id.btn_add_task);

        // click to btn add
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {
                    userPlanerDaoProvider.addNewTask(new PlanerDetails());
                    tasksList = userPlanerDaoProvider.getTaskList();
                    runOnUiThread(() -> adapter.setPlaner(tasksList));

                }).start();
                Timber.d("onClickBTNAdd%s", this);
                Timber.i(tasksList.toString());
            }
        });


        Timber.d("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Timber.d("onStart");
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

        Timber.d("onResume");
    }


}
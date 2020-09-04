package com.gmail.kaminskysem.PersnalHelper.planerTODOlist;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    private RecyclerView rvPlanerTask;
    private PlanerDetailsAdapter adapter;
    private Button btnAddTask;
    private EditText etNewTask;
    private UserPlanerProvider userPlanerProvider;
    private String textTask;
    private PlanerDetails newTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer_todo_list);

        tasksList = new InMemoryUserPlanerProvider().getPlanerDetails();
        adapter = new PlanerDetailsAdapter();
        // нужный ли метод вызвал?
//        adapter.setPlaner(tasksList);

        rvPlanerTask = findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));
        userPlanerProvider = UserPlanerProvider.getInstance();


        btnAddTask = (Button) findViewById(R.id.btn_add_task);

        // click to btn add
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 newTask = new PlanerDetails(-1, "", false);
                tasksList.add(0, newTask);
                Log.d(LOG_TAG, "onClickBTNAdd" + this);
                userPlanerProvider.addNewTask(newTask);
                adapter.setPlaner(tasksList);
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
            etNewTask = findViewById(R.id.et_new_task);
            if (tasksList!=null&&etNewTask!=null){
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etNewTask, InputMethodManager.SHOW_IMPLICIT);
                etNewTask.requestFocus();
            }
        //TODO  добавить автокликера для едит текст

            adapter.setItemClickListener(new PlanerDetailsItemListener() {
                @Override
                public void onTaskItemClick(int itemId, PlanerDetails planerDetails) {
                    if (textTask != null) {
                    textTask = etNewTask.getText().toString();
                        newTask.setStringTask(textTask);
                        adapter.notifyDataSetChanged();
                    }
                    userPlanerProvider.updateTaskWithID(itemId, planerDetails);
                    adapter.notifyDataSetChanged();
                }
            });





//        adapter.setPlaner(userPlanerProvider.getCardsDetails());

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
package com.gmail.kaminskysem.PersnalHelper.planer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetails;
import com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model.PlanerDetailsAdapter;

import java.util.List;

public final class PlanerActivity extends AppCompatActivity {
   private static String LOG_TAG = PlanerActivity.class.getSimpleName();
    private List<PlanerDetails>cardsList;
    private RecyclerView rvPlanerTask;
    private PlanerDetailsAdapter adapter;
    private  Button btnAddTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planer);

        adapter=new PlanerDetailsAdapter();
        rvPlanerTask=findViewById(R.id.rv_planer);
        rvPlanerTask.setAdapter(adapter);
        rvPlanerTask.setLayoutManager(new LinearLayoutManager(this));

        cardsList = new UserPlanerProvider().getCardsDetails();
        adapter.setCards(cardsList);

        Log.d(LOG_TAG, "onCreate"+this);
    }

    // TODO: 15.06.2020   передалать список в recycleView


    @Override
    protected void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "onStart"+this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "onResume"+this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "onPause"+this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(LOG_TAG, "onStop"+this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(LOG_TAG, "onDestroy"+this);

    }
}
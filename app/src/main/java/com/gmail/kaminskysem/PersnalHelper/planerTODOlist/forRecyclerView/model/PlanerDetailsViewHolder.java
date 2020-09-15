package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data.DataProviderFactory;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data.InMemoryUserPlanerProvider;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IPlanerDetailsItemListener;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;

import java.util.List;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder {
    private final static String LOG = PlanerDetailsViewHolder.class.getSimpleName();
    private final EditText editTextTask;
    private final CheckBox checkTask;
    private final TextView tvIdTask;
    private TextWatcher textWatcher;
//    private InMemoryUserPlanerProvider inMemoryUserPlanerProvider = InMemoryUserPlanerProvider.getInstance();
private IUserPlanerDao userPlanerDaoProvider;


    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        editTextTask = itemView.findViewById(R.id.et_new_task);
        checkTask = itemView.findViewById(R.id.cb_checkbox_task);
        tvIdTask = itemView.findViewById(R.id.tv_text_id);
        userPlanerDaoProvider = DataProviderFactory.getDataProvider(itemView.getContext());




    }

    @SuppressLint("SetTextI18n")
    void Bind (PlanerDetails planerDetails) {

        if(textWatcher !=null){
            editTextTask.removeTextChangedListener(textWatcher);
        }
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // save to db

                planerDetails.setTaskString(s.toString());
                new Thread(()->{
                    userPlanerDaoProvider.updateTaskWithId(planerDetails);
                }).start();

            }

        };

        tvIdTask.setText(planerDetails.getTaskID().toString());
        editTextTask.addTextChangedListener(textWatcher);

        editTextTask.setText(planerDetails.getTaskString());
        checkTask.setChecked(planerDetails.getCheckTask());



    }





}
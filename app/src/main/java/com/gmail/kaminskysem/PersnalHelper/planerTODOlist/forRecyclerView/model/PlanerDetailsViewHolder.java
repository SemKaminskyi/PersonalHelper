package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.MyApp;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.PlanerActivity;

import javax.inject.Inject;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder {
    private final static String LOG = PlanerDetailsViewHolder.class.getSimpleName();
    private final EditText editTextTask;
    private final CheckBox checkTask;
    private final TextView tvIdTask;
    private TextWatcher textWatcher;
    @Inject
    public IUserPlanerDao userPlanerDaoProvider;


    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        editTextTask = itemView.findViewById(R.id.et_new_task);
        checkTask = itemView.findViewById(R.id.cb_checkbox_task);
        tvIdTask = itemView.findViewById(R.id.tv_text_id);
        MyApp.getApplicationsComponent(itemView.getContext()).inject (this);

    }

    @SuppressLint("SetTextI18n")
    void Bind (PlanerDetails planerDetails) {
        if(textWatcher !=null){
            editTextTask.removeTextChangedListener(textWatcher);
        }
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

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

//        checkTask.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (buttonView.isChecked()){
//                    Thread one = new Thread(()->{
//                        userPlanerDaoProvider.delete(planerDetails);
//                    });
//                            one.start();
//
//                    try {
//                        one.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // adapter is NULL ??
////                    planerDetailsAdapter.notifyItemRemoved(getAdapterPosition());
//                }
//            }
//        });




    }





}
package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.Data.InMemoryUserPlanerProvider;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IPlanerDetailsItemListener;

import java.util.List;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder implements IPlanerDetailsItemListener {
    private final static String LOG = PlanerDetailsViewHolder.class.getSimpleName();
    private final EditText editTextTask;
    private final CheckBox checkTask;
    private final TextView tvIdTask;
    private TextWatcher textWatcher;
//    private InMemoryUserPlanerProvider inMemoryUserPlanerProvider = InMemoryUserPlanerProvider.getInstance();
    private InMemoryUserPlanerProvider inMemoryUserPlanerProvider = new InMemoryUserPlanerProvider();
    private List<PlanerDetails>planerDetailsList = inMemoryUserPlanerProvider.getPlanerDetails();



    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        editTextTask = itemView.findViewById(R.id.et_new_task);
        checkTask = itemView.findViewById(R.id.cb_checkbox_task);
        tvIdTask = itemView.findViewById(R.id.tv_text_id);



    }

    void Bind (PlanerDetails planerDetails){
        editTextTask.setText(planerDetails.getTaskString());
        checkTask.setChecked(planerDetails.getCheckTask());
        tvIdTask.setText(planerDetails.getTaskID().toString());

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
//                int id = Math.toIntExact(planerDetails.getTaskID());
//                planerDetailsList.get(id).setTaskString(s.toString());
                planerDetails.setTaskString(s.toString());

//                inMemoryUserPlanerProvider.updateTaskWithID(planerDetails.getTaskID(),planerDetails);
            }

        };

        editTextTask.addTextChangedListener(textWatcher);
    }



// метод не вызывается, почему-то((
    @Override
    public void onTaskItemClick(int itemId, PlanerDetails planerDetails) {
        Log.d(LOG, "on item click");
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
//                int id = Math.toIntExact(planerDetails.getTaskID());
//                planerDetailsList.get(id).setTaskString(s.toString());
                planerDetails.setTaskString(s.toString());

//                inMemoryUserPlanerProvider.updateTaskWithID(planerDetails.getTaskID(),planerDetails);
            }

        };

        editTextTask.addTextChangedListener(textWatcher);
    }


}
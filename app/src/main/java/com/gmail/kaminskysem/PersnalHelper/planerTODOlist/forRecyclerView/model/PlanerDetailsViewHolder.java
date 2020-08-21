package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder {
    private final EditText task;


    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        task=itemView.findViewById(R.id.et_new_task);
    }
    void bind (PlanerDetails tasks){
        task.setText(tasks.getTask());
    }
}
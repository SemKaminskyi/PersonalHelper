package com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder {
    private final TextView task;


    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        task=itemView.findViewById(R.id.et_new_task);
    }
    void bind (PlanerDetails card){
        task.setText(card.getTask());
    }
}
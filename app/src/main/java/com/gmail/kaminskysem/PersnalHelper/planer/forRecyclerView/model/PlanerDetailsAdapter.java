package com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

import java.util.ArrayList;
import java.util.List;

public class PlanerDetailsAdapter extends RecyclerView.Adapter<PlanerDetailsViewHolder> {
    private final List<PlanerDetails> tasks= new ArrayList<>();

    public void setPlaner(List<PlanerDetails> task){
        this.tasks.clear();
        this.tasks.addAll(task);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_details,parent,false);

        return new PlanerDetailsViewHolder(v);

        }

    @Override
    public void onBindViewHolder(@NonNull PlanerDetailsViewHolder holder, int position) {
        PlanerDetails task =tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
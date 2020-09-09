package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

import java.util.ArrayList;
import java.util.List;

public class PlanerDetailsAdapter extends RecyclerView.Adapter<PlanerDetailsViewHolder> {
    private final List<PlanerDetails> planerDetailsList =new ArrayList<>();

    public void setPlaner(List<PlanerDetails> task){
        this.planerDetailsList.clear();
        this.planerDetailsList.addAll(task);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PlanerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_details,parent,false);
        return new PlanerDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanerDetailsViewHolder holder, int position) {
        PlanerDetails planerDetails = planerDetailsList.get(position);
        holder.Bind(planerDetails);
    }

    @Override
    public int getItemCount() {
        return planerDetailsList.size();
    }
}

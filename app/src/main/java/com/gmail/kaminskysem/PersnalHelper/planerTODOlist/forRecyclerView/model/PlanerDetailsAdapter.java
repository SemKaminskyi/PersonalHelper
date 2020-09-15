package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;

import java.util.ArrayList;
import java.util.List;

public class PlanerDetailsAdapter extends RecyclerView.Adapter<PlanerDetailsViewHolder> {
    private final List<PlanerDetails> planerDetailsList =new ArrayList<>();
    private IUserPlanerDao userPlanerDaoProvider;

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
        CheckBox checkBox = holder.itemView.findViewById(R.id.cb_checkbox_task);
        PlanerDetails planerDetails = planerDetailsList.get(position);
        if (checkBox.isChecked()){
            userPlanerDaoProvider.delete(planerDetails);

            notifyDataSetChanged();
        }
        holder.Bind(planerDetails);
    }

    @Override
    public int getItemCount() {
        return planerDetailsList.size();
    }
}

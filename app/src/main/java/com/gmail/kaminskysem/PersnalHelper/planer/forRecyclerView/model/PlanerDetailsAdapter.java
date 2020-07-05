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
    private final List<PlanerDetails> cards= new ArrayList<>();

    public void setCards(List<PlanerDetails> cards){
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanerDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carddetails,parent,false);

        return new PlanerDetailsViewHolder(v);

        }

    @Override
    public void onBindViewHolder(@NonNull PlanerDetailsViewHolder holder, int position) {
        PlanerDetails card =cards.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}

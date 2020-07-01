package com.gmail.kaminskysem.PersnalHelper.planer.forRecyclerView.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

import java.util.ArrayList;
import java.util.List;

public class CardDetailsAdapter extends RecyclerView.Adapter<CardDetailsViewHolder> {
    private final List<CardDetails> cards= new ArrayList<>();

    public void setCards(List<CardDetails> cards){
        this.cards.clear();
        this.cards.addAll(cards);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carddetails,parent,false);

        return new CardDetailsViewHolder(parent);

        }

    @Override
    public void onBindViewHolder(@NonNull CardDetailsViewHolder holder, int position) {
        CardDetails card =cards.get(position);
//        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}

package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.MyApp;
import com.gmail.kaminskysem.PersnalHelper.R;
import com.gmail.kaminskysem.PersnalHelper.planerTODOlist.IUserPlanerDao;

import java.util.List;

import javax.inject.Inject;

public class PlanerDetailsAdapter extends RecyclerView.Adapter<PlanerDetailsViewHolder> {

    @Inject
    public IUserPlanerDao userPlanerDaoProvider;
    private  List<PlanerDetails> planerDetailsList;


    public PlanerDetailsAdapter(Context context) {
        MyApp.getApplicationsComponent(context).inject (this);
        new Thread(()-> planerDetailsList = userPlanerDaoProvider.getTaskList()).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setPlaner(List<PlanerDetails> task){
        Thread one =new Thread(()-> planerDetailsList = userPlanerDaoProvider.getTaskList());
        one.start();
        try {
            one.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        CheckBox checkBox = holder.itemView.findViewById(R.id.cb_checkbox_task);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (checkBox.isChecked()) {
                Thread one = new Thread(()-> userPlanerDaoProvider.delete(planerDetails));
                one.start();
                try {
                    one.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notifyDataSetChanged();
            }
        });
                holder.Bind(planerDetails);
    }
    @Override

    public int getItemCount() {

       Thread one = new  Thread(()-> planerDetailsList = userPlanerDaoProvider.getTaskList());
        one.start();
        try {
            one.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return planerDetailsList.size();
    }
}

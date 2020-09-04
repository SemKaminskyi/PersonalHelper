package com.gmail.kaminskysem.PersnalHelper.planerTODOlist.forRecyclerView.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.kaminskysem.PersnalHelper.R;

public class PlanerDetailsViewHolder extends RecyclerView.ViewHolder {
    private final EditText editText;
    private TextWatcher textWatcher;


    public PlanerDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        editText =itemView.findViewById(R.id.et_new_task);
    }
    void bind (PlanerDetails task){
        if(textWatcher !=null){
            editText.removeTextChangedListener(textWatcher);
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

                task.setStringTask(s.toString());
//                editText.clearFocus();
            }
        };

        editText.addTextChangedListener(textWatcher);


    }
}
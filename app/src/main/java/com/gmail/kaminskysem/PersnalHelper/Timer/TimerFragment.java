package com.gmail.kaminskysem.PersnalHelper.Timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class imerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static imerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        imerFragment fragment = new imerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

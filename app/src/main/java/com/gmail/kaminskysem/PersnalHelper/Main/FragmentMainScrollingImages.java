package com.gmail.kaminskysem.PersnalHelper.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.gmail.kaminskysem.PersnalHelper.R;


public class FragmentMainScrollingImages extends Fragment {
    private final static String LOG_TAG = FragmentMainScrollingImages.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_scrolling_images, container, false);
    }
}
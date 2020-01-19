package com.example.popularmoviesstage2.detail;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmoviesstage2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMoveDetail extends Fragment {


    public FragmentMoveDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

}

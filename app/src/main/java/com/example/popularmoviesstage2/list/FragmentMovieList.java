package com.example.popularmoviesstage2.list;


import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmoviesstage2.MovieApplication;
import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.databinding.FragmentMovieListBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieList extends Fragment {

    FragmentMovieListBinding binding;
    ViewModelMovieList viewModelMovieList;

    public FragmentMovieList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO(2): Create a ViewModel
        ViewModelListFactory factory = new ViewModelListFactory(((MovieApplication)requireContext().getApplicationContext()).getRepository());
        viewModelMovieList = ViewModelProviders.of(this, factory).get(viewModelMovieList.getClass());

        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModelMovieList);
        binding.setLifecycleOwner(this);

        //recycler view
        binding.recyclerMovieList.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recyclerMovieList.setLayoutManager(layoutManager);
        AdapterMovieList adapter = new AdapterMovieList();
        binding.recyclerMovieList.setAdapter(adapter);

        //download data from remote source.
        viewModelMovieList.initValue();
        return binding.getRoot();
    }



}

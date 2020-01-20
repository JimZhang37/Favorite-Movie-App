package com.example.popularmoviesstage2.list;


import android.app.Application;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmoviesstage2.MovieApplication;
import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.databinding.FragmentMovieListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieList extends Fragment {

    FragmentMovieListBinding binding;
    ViewModelMovieList viewModelMovieList;
    AdapterMovieList adapter;

    public FragmentMovieList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO(2): Create a ViewModel
        ViewModelListFactory factory = new ViewModelListFactory(((MovieApplication) requireContext().getApplicationContext()).getRepository());
        viewModelMovieList = ViewModelProviders.of(this, factory).get(ViewModelMovieList.class);

        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModelMovieList);
        binding.setLifecycleOwner(this);

        //recycler view
        binding.recyclerMovieList.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recyclerMovieList.setLayoutManager(layoutManager);
        adapter = new AdapterMovieList();
        binding.recyclerMovieList.setAdapter(adapter);

        // register livedata observer
        registerLivedataObserver();
        //download data from remote source.
        viewModelMovieList.initValue();
        return binding.getRoot();
    }

    /**
     *
     */
    private void registerLivedataObserver() {
        viewModelMovieList.dataTopRated.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.updateData(movies);
            }
        });
    }




}

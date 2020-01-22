package com.example.popularmoviesstage2.detail;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.popularmoviesstage2.MovieApplication;
import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.databinding.FragmentMovieDetailBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMoveDetail extends Fragment {
    private FragmentMovieDetailBinding binding;
    private ViewModelMovieDetail viewModelMovieDetail;
    private AdapterReviewList adapterReviewList;
    public FragmentMoveDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
// Inflate the layout for this fragment
        String argument = getArguments().getString("movieID");
        //TODO(2): Create a ViewModel
        ViewModelDetailFactory factory = new ViewModelDetailFactory(((MovieApplication) requireContext().getApplicationContext()).getRepository(),argument);
        viewModelMovieDetail = ViewModelProviders.of(this, factory).get(ViewModelMovieDetail.class);

        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        binding.recyclerReviewList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerReviewList.setLayoutManager(linearLayoutManager);
        adapterReviewList = new AdapterReviewList();
        binding.recyclerReviewList.setAdapter(adapterReviewList);

        observeData();
        return binding.getRoot();
    }

    private void observeData(){
        viewModelMovieDetail.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.setMovie(movie);
            }
        });

        viewModelMovieDetail.getReviews().observe(this,new Observer<List<Review>>(){
            @Override
            public void onChanged(List<Review> reviews) {
                adapterReviewList.updateData(reviews);
            }
        });
    }

}

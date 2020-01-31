package com.example.popularmoviesstage2.detail;


import androidx.appcompat.app.ActionBar;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.example.popularmoviesstage2.MovieApplication;
import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.Trailer;
import com.example.popularmoviesstage2.databinding.FragmentMovieDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMoveDetail extends Fragment implements AdapterTrailerList.ListItemClickListener{
    private FragmentMovieDetailBinding binding;
    private ViewModelMovieDetail viewModelMovieDetail;
    private AdapterReviewList adapterReviewList;
    private AdapterTrailerList adapterTrailerList;
    private ActionBar ab;
    public FragmentMoveDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
// Inflate the layout for this fragment
        String argument = getArguments().getString("movieID");
        String movieName = getArguments().getString("movieName");
        //TODO(2): Create a ViewModel
        ViewModelDetailFactory factory = new ViewModelDetailFactory(((MovieApplication) requireContext().getApplicationContext()).getRepository(), argument);
        viewModelMovieDetail = ViewModelProviders.of(this, factory).get(ViewModelMovieDetail.class);

        binding = FragmentMovieDetailBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        binding.recyclerReviewList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerReviewList.setLayoutManager(linearLayoutManager);
        adapterReviewList = new AdapterReviewList();
        binding.recyclerReviewList.setAdapter(adapterReviewList);

        binding.recyclerTrailerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        binding.recyclerTrailerList.setLayoutManager(linearLayoutManager1);
        adapterTrailerList = new AdapterTrailerList(this);
        binding.recyclerTrailerList.setAdapter(adapterTrailerList);

        observeData();

        binding.toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.toggleButton.isChecked()){
                    viewModelMovieDetail.saveFaovite();
                }
                else{
                    viewModelMovieDetail.deleteFavorite();
                }
            }
        });


        Toolbar toolbar = binding.detailToolbar;
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setTitle(movieName);


        setupNavigationUp();
        return binding.getRoot();
    }

    private void observeData() {
        viewModelMovieDetail.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.setMovie(movie);
                String url = "http://image.tmdb.org/t/p/w500/" + movie.getImage();
                Picasso.get().load(url).into(binding.imageDetail);
                Picasso.get().load(url).into(binding.imageAppbar);
                ab.setTitle(movie.getTitle());
            }

        });

        viewModelMovieDetail.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapterReviewList.updateData(reviews);
            }
        });

        viewModelMovieDetail.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                adapterTrailerList.updateData(trailers);
            }
        });

        viewModelMovieDetail.getFavorite().observe(this, new Observer<Movie_Favorite>() {
            @Override
            public void onChanged(Movie_Favorite movie_favorite) {
                if(movie_favorite == null){
                    binding.toggleButton.setChecked(false);
                }
                else{
                    binding.toggleButton.setChecked(true);
                }

            }
        });
    }

    /**
     * TODO(it's not working )
     */
    private void setupNavigationUp(){

        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onListItemClick(String key) {
//        String key = model.getTrailerData().getValue().get(index).getKey();
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + key));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key));
        try {
            Log.d("WebIntent", "webintent");
            startActivity(webIntent);//TODO when tested in real phone, swap the webIntent and appIntent;
        } catch (ActivityNotFoundException ex) {
            startActivity(appIntent);
            Log.d("appIntent", "appintent");
        }
    }
}

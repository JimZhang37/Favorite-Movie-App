package com.example.popularmoviesstage2.list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovieList extends RecyclerView.Adapter<ImageViewHolder> {

    private ArrayList<Movie> movieList;
    public void updateData(ArrayList<Movie> movies){
        movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

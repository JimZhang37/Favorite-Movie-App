package com.example.popularmoviesstage2.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovieList extends RecyclerView.Adapter<ImageViewHolder> {

    private List<Movie> movieList;
    public void updateData(List<Movie> movies){
        movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_image, parent,false);
        ImageViewHolder holder = new ImageViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String path = "http://image.tmdb.org/t/p/w185/" + movieList.get(position).getImage();
        holder.updateWithUrl(path);
    }

    @Override
    public int getItemCount() {

        if (movieList == null){ return 0;}
        return movieList.size();
    }
}

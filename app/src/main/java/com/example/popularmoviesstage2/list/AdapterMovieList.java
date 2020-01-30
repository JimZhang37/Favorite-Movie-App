package com.example.popularmoviesstage2.list;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;
import com.squareup.picasso.Picasso;


import java.util.List;

public class AdapterMovieList extends RecyclerView.Adapter<AdapterMovieList.ImageViewHolder> {
    interface ListItemClickListener {
        void onListItemClick(View v, String movieId, String movieName);
    }

    public AdapterMovieList(ListItemClickListener listener) {
        mLisnter = listener;
    }

    private List<Movie> movieList;
    private ListItemClickListener mLisnter;

    public void updateData(List<Movie> movies) {
        movieList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_image, parent, false);
        ImageViewHolder holder = new ImageViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String path = "http://image.tmdb.org/t/p/w500/" + movieList.get(position).getImage();
//        holder.updateWithUrl(path);
        holder.bind(path,mLisnter,movieList.get(position).getMovieID(), movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {

        if (movieList == null) {
            return 0;
        }
        return movieList.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView mMoviePoster;

        public ImageViewHolder(@NonNull ImageView itemView) {
            super(itemView);

            mMoviePoster = itemView;

        }

//        public void updateWithUrl(String url) {
//            Picasso
//                    .get()
//                    .load(url)
//                    .into(mMoviePoster);
//        }

        public void bind(String url, ListItemClickListener listener, String movieId, String movieName) {
            Picasso
                    .get()
                    .load(url)
                    .into(mMoviePoster);


            mMoviePoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onListItemClick(v,movieId, movieName);
                }
            } );

        }
    }
}

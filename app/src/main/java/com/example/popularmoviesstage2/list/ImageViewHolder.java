package com.example.popularmoviesstage2.list;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesstage2.R;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView mMoviePoster;
    public ImageViewHolder(@NonNull ImageView itemView) {
        super(itemView);

        mMoviePoster =  itemView;
    }
    public void updateWithUrl(String url) {
        Picasso
                .get()
                .load(url)

                .into(mMoviePoster);

        //mMovieTitle.text = url
    }
}

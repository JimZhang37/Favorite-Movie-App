package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;

import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.source.local.FavoriteDao;


public class DeleteFavoriteAsyncTask extends AsyncTask<Void, Void, Void> {

    private FavoriteDao favoriteDao;
    private Movie_Favorite mMovie;

    public DeleteFavoriteAsyncTask(FavoriteDao dao, Movie_Favorite movie) {
        this.favoriteDao = dao;
        this.mMovie = movie;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        favoriteDao.deleteFavorite(mMovie);
        return null;
    }
}



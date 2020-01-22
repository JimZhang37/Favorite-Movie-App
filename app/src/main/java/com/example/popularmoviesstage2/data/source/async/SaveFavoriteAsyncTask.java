package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;
import com.example.popularmoviesstage2.data.Movie_Favorite;
import com.example.popularmoviesstage2.data.source.local.FavoriteDao;


public class SaveFavoriteAsyncTask extends AsyncTask<Void, Void, Void> {

    private FavoriteDao favoriteDao;
    private Movie_Favorite mMovie;

    public SaveFavoriteAsyncTask(FavoriteDao dao, Movie_Favorite movie) {
        this.favoriteDao = dao;
        this.mMovie = movie;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        favoriteDao.insertFavorite(mMovie);
        return null;
    }
}



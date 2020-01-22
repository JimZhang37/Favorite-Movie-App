package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;


import com.example.popularmoviesstage2.data.Trailer;
import com.example.popularmoviesstage2.data.TrailerRemote;
import com.example.popularmoviesstage2.data.source.local.TrailerDao;

import java.util.List;

public class SaveTrailersAsyncTask extends AsyncTask<Void, Void, Void> {

    private List<TrailerRemote> trailerList;
    private TrailerDao trailerDao;
    private String mMovieID;

    public SaveTrailersAsyncTask(List<TrailerRemote> trailerList, TrailerDao trailerDao, String mMovieID) {
        this.trailerList = trailerList;
        this.trailerDao = trailerDao;
        this.mMovieID = mMovieID;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        trailerList.forEach(trailerRemote -> {
            trailerDao.insertTrailer(new Trailer(trailerRemote,mMovieID));
        });
        return null;
    }
}



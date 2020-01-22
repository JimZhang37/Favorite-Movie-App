package com.example.popularmoviesstage2.data.source.async;

import android.os.AsyncTask;

import com.example.popularmoviesstage2.data.Review;
import com.example.popularmoviesstage2.data.ReviewRemote;
import com.example.popularmoviesstage2.data.source.local.ReviewDao;

import java.util.List;

public class SaveReviewsAsyncTask extends AsyncTask<Void, Void, Void> {
    private List<ReviewRemote> reviewList;
    private ReviewDao reviewDao;
    private String mMovieID;

    public SaveReviewsAsyncTask(List<ReviewRemote> list, ReviewDao dao, String movieID) {
        reviewList = list;
        reviewDao = dao;
        mMovieID = movieID;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        reviewList.forEach(reviewRemote -> {
            reviewDao.insertReveiw(new Review(reviewRemote,mMovieID));
        });

        return null;
    }
}

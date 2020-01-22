package com.example.popularmoviesstage2.list;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.popularmoviesstage2.MovieApplication;
import com.example.popularmoviesstage2.R;
import com.example.popularmoviesstage2.data.Movie;
import com.example.popularmoviesstage2.databinding.FragmentMovieListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieList extends Fragment implements AdapterMovieList.ListItemClickListener {
    private String SharedPreferenceKey = "movie_type";
    FragmentMovieListBinding binding;
    ViewModelMovieList viewModelMovieList;
    AdapterMovieList adapter;
//    private Toolbar toolbar;
//    private Toolbar.OnMenuItemClickListener menuListener;

    public FragmentMovieList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //TODO(2): Create a ViewModel
        ViewModelListFactory factory = new ViewModelListFactory(((MovieApplication) requireContext().getApplicationContext()).getRepository());
        viewModelMovieList = ViewModelProviders.of(this, factory).get(ViewModelMovieList.class);

        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModelMovieList);
        binding.setLifecycleOwner(this);

        //recycler view
        binding.recyclerMovieList.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recyclerMovieList.setLayoutManager(layoutManager);
        adapter = new AdapterMovieList(this);
        binding.recyclerMovieList.setAdapter(adapter);

        // register livedata observer
//        registerLivedataObserver();
        setAdapter(fetchPreference());
        //download data from remote source.
        viewModelMovieList.initValue();

        setHasOptionsMenu(true);
//        toolbar = container.findViewById(R.id.toolbar);


        return binding.getRoot();
    }



    //
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        toolbar.inflateMenu(R.menu.list_menu);
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        menuListener = new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                int itemId = item.getItemId();
//                Log.d("menu item ID is ", String.valueOf(itemId));
//                switch (itemId) {
//                    /*         * When you click the reset menu item, we want to start all over
//                     * and display the pretty gradient again. There are a few similar
//                     * ways of doing this, with this one being the simplest of those
//                     * ways. (in our humble opinion)*/
//                    case R.id.popular_test:
////                tx.setText("Popular Movie");
//////                mRV.setAdapter(mAdapter);
////                model.setData(1);
//
//
//                        return true;
//                    case R.id.top_rated_test:
////                tx.setText("Top Rated Movie");
//////                mRV.setAdapter(mAdapter);
////                model.setData(2);
//
//
//                        return true;
//                    case R.id.favorite_test:
////                Class destinationActivity = FavoriteMovieActivity.class;
////                Intent intent = new Intent(TestActivity.this, destinationActivity);
////
////                startActivity(intent);
////                mRV.setAdapter(mAdapterFavorite);
//
//                        return true;
//                }
//                return false;
//            }
//
//        };
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        toolbar.setOnMenuItemClickListener(menuListener);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        toolbar.setOnMenuItemClickListener(null);
//    }

    /**
     *
     */
    private void registerLivedataObserver() {
        viewModelMovieList.dataTopRated.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.updateData(movies);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        Log.d("menu item ID is ", String.valueOf(itemId));
        switch (itemId) {
            /*         * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)*/
            case R.id.popular_test:

                savePreference(1);
                return setAdapter(1);
            case R.id.top_rated_test:
                savePreference(2);
                return setAdapter(2);

            case R.id.favorite_test:

                return true;
        }
        return super.onOptionsItemSelected(item);//TODO why return?
    }

    private boolean setAdapter(int movieType) {
        viewModelMovieList.dataPopular.removeObservers(this);
        viewModelMovieList.dataTopRated.removeObservers(this);

        switch (movieType) {
            case 1:
                viewModelMovieList.dataPopular.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        adapter.updateData(movies);
                    }
                });
                return true;
            case 2:
                viewModelMovieList.dataTopRated.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        adapter.updateData(movies);
                    }
                });
                return true;
            case 3:
                return true;
            default:
                return false;

        }
    }

    private void savePreference(int movieType) {
        Log.d( "before save Preference", "ii is:" + movieType);
        SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt(SharedPreferenceKey, movieType);
        editor.apply();
        int i = getActivity().getPreferences(Context.MODE_PRIVATE).getInt(SharedPreferenceKey, 1);
        Log.d( "Saved Preference", "ii is:" + i);
        switch (movieType) {
            case 1:
                Toast.makeText(getActivity(),"Popular Movie", Toast.LENGTH_LONG);
                return;
            case 2:
                Toast.makeText(getActivity(),"Top Rated Movie", Toast.LENGTH_LONG);

                return;
            default:
                return;

        }
    }

    private int fetchPreference(){
        int i = getActivity().getPreferences(Context.MODE_PRIVATE).getInt(SharedPreferenceKey, 1);
        if (i >3 || i < 1){
            i = 1;
            SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
            editor.putInt(SharedPreferenceKey, i);
            editor.apply();
        }
        Log.d("Preference is", "i is:" + i);
        return i;
    }

    @Override
    public void onListItemClick(View v, String movieId) {
        Log.d("ABC", "onListItemClick");
        FragmentMovieListDirections.ActionFragmentMovieListToFragmentMoveDetail action = FragmentMovieListDirections.actionFragmentMovieListToFragmentMoveDetail(movieId);
        Navigation.findNavController(v).navigate(action);
    }
}

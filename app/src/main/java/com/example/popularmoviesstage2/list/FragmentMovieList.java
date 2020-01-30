package com.example.popularmoviesstage2.list;



import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;

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


import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovieList extends Fragment implements AdapterMovieList.ListItemClickListener {
    private String SharedPreferenceKey = "movie_type";
    static private int dataUpdate = 0;
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
//        viewModelMovieList.initValue();
        //TODO Add action bar
//        Toolbar toolbar = binding.toolbar;
//        getActivity().get
        setHasOptionsMenu(true);
//        toolbar = container.findViewById(R.id.toolbar);


        return binding.getRoot();
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
                savePreference(3);
                return setAdapter(3);
            case R.id.update_data:
                viewModelMovieList.initValue();
                return true;
        }
        return super.onOptionsItemSelected(item);//TODO why return?
    }

    private boolean setAdapter(int movieType) {
        viewModelMovieList.dataPopular.removeObservers(this);
        viewModelMovieList.dataTopRated.removeObservers(this);
        viewModelMovieList.dataFavorite.removeObservers(this);

        switch (movieType) {
            case 1:
                viewModelMovieList.dataPopular.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        updateDateTimes();
                        adapter.updateData(movies);
                        getActivity().setTitle("Popular");
                    }
                });
                return true;
            case 2:
                viewModelMovieList.dataTopRated.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        updateDateTimes();
                        adapter.updateData(movies);
                        getActivity().setTitle("Top Rated");
                    }
                });
                return true;
            case 3:
                viewModelMovieList.dataFavorite.observe(this, new Observer<List<Movie>>() {
                    @Override
                    public void onChanged(List<Movie> movies) {
                        updateDateTimes();
                        adapter.updateData(movies);
                        getActivity().setTitle("Favorite");
                    }
                });
                return true;
            default:
                return false;

        }
    }

    private void savePreference(int movieType) {
        Log.d("before save Preference", "ii is:" + movieType);
        SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
        editor.putInt(SharedPreferenceKey, movieType);
        editor.apply();
        int i = getActivity().getPreferences(Context.MODE_PRIVATE).getInt(SharedPreferenceKey, 1);
        Log.d("Saved Preference", "ii is:" + i);
        switch (movieType) {
            case 1:
                Toast.makeText(getActivity(), "Popular Movie", Toast.LENGTH_LONG);
                return;
            case 2:
                Toast.makeText(getActivity(), "Top Rated Movie", Toast.LENGTH_LONG);

                return;
            default:
                return;

        }
    }

    private int fetchPreference() {
        int i = getActivity().getPreferences(Context.MODE_PRIVATE).getInt(SharedPreferenceKey, 1);
        if (i > 3 || i < 1) {
            i = 1;
            SharedPreferences.Editor editor = getActivity().getPreferences(Context.MODE_PRIVATE).edit();
            editor.putInt(SharedPreferenceKey, i);
            editor.apply();
        }
        Log.d("Preference is", "i is:" + i);
        return i;
    }

    private void updateDateTimes() {
        dataUpdate++;
        Log.d("the times of data update is ", ":" + dataUpdate);
        Toast.makeText(getContext(), "the times of data update is: " + dataUpdate, Toast.LENGTH_SHORT);
    }

    @Override
    public void onListItemClick(View v, String movieId) {
        Log.d("ABC", "onListItemClick");
        FragmentMovieListDirections.ActionFragmentMovieListToFragmentMoveDetail action = FragmentMovieListDirections.actionFragmentMovieListToFragmentMoveDetail(movieId);
        Navigation.findNavController(v).navigate(action);
    }
}

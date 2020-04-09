package com.pressure.movieinfo.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.pressure.movieinfo.model.Movie;
import com.pressure.movieinfo.model.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Movie>> getMoviesList() {
        return repository.getMoviesList();
    }
}

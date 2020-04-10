package com.pressure.movieinfo.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.pressure.movieinfo.model.Movie;
import com.pressure.movieinfo.model.MovieDataSource;
import com.pressure.movieinfo.model.MovieDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {
    private MovieDataSourceFactory movieDataSourceFactory;

    private  Executor executor;
    private LiveData<PagedList<Movie>> movies;
    public MainViewModel(@NonNull Application application) {
        super(application);
        movieDataSourceFactory = new MovieDataSourceFactory(application);
        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                                      .setInitialLoadSizeHint(10)
                                      .setPageSize(20)
                                      .setPrefetchDistance(4).build();

        executor = Executors.newFixedThreadPool(4);
        movies = (new LivePagedListBuilder<Long,Movie>(movieDataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Movie>> getMovies() {
        return movies;
    }
}

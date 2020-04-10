package com.pressure.movieinfo.model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MovieDataSourceFactory extends DataSource.Factory<Long,Movie> {
    private Application application;
    private MutableLiveData<MovieDataSource> liveData;
    private MovieDataSource dataSource;
    public MovieDataSourceFactory(Application application) {
        this.application = application;
        liveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Long, Movie> create() {
        dataSource = new MovieDataSource(application);
        liveData.postValue(dataSource);
        return dataSource;
    }

    public MutableLiveData<MovieDataSource> getLiveData() {
        return liveData;
    }
}

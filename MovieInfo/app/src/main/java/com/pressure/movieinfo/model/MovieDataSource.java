package com.pressure.movieinfo.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.pressure.movieinfo.R;
import com.pressure.movieinfo.apiservice.APIService;
import com.pressure.movieinfo.client.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long,Movie> {
    private APIClient client;
    private Application application;

    public MovieDataSource(Application application) {
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        client = APIService.getClient().create(APIClient.class);
        Call<MovieDBResponse> call = client.getPopularMoviesPaging(application.getApplicationContext().getString(R.string.api_key),(long)1);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse dbResponse = response.body();
                List<Movie> movies  = new ArrayList<Movie>();
                if(dbResponse != null && (dbResponse.getMovies() != null))
                {
                    movies = dbResponse.getMovies();
                }
                callback.onResult(movies,null,(long)2);

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {

        Call<MovieDBResponse> call = client.getPopularMoviesPaging(application.getApplicationContext().getString(R.string.api_key),(long)params.key);
        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse dbResponse = response.body();
                List<Movie> movies  = new ArrayList<Movie>();
                if(dbResponse != null && (dbResponse.getMovies() != null))
                {
                    movies = dbResponse.getMovies();
                }
                callback.onResult(movies,(long)params.key+1);

            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });


    }
}

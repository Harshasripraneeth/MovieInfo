package com.pressure.movieinfo.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.pressure.movieinfo.R;
import com.pressure.movieinfo.apiservice.APIService;
import com.pressure.movieinfo.client.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository
{
    private MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    private Application application;
    public Repository(Application application)
    {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMoviesList()
    {
        APIClient client = APIService.getClient().create(APIClient.class);

        Call<MovieDBResponse> call = client.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDBResponse>() {
            @Override
            public void onResponse(Call<MovieDBResponse> call, Response<MovieDBResponse> response) {
                MovieDBResponse dbResponse = response.body();

                //MovieDBResponse and List of Movies should not be null

                if(dbResponse != null && dbResponse.getMovies() != null)
                {
                    movies.setValue((ArrayList<Movie>)dbResponse.getMovies());
                }
            }

            @Override
            public void onFailure(Call<MovieDBResponse> call, Throwable t) {

            }
        });
        return  movies;
    }
}

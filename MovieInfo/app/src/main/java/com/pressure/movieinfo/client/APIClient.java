package com.pressure.movieinfo.client;

import com.pressure.movieinfo.model.MovieDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIClient {

    @GET("movie/popular")
    Call<MovieDBResponse> getPopularMovies(@Query("api_key") String apiKey);

}

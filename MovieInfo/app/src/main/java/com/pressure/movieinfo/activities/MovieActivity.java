package com.pressure.movieinfo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.pressure.movieinfo.R;
import com.pressure.movieinfo.databinding.ActivityMovieBinding;
import com.pressure.movieinfo.model.Movie;

public class MovieActivity extends AppCompatActivity {
    ActivityMovieBinding activityMovieBinding;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieBinding = DataBindingUtil.setContentView(this,R.layout.activity_movie);
        toolbar = activityMovieBinding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        if(getIntent().hasExtra("movie"))
        {
           Movie movie = getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);
            getSupportActionBar().setTitle(movie.getTitle());
        }


    }
}

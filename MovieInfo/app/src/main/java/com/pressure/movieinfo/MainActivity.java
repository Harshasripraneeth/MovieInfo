package com.pressure.movieinfo;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import com.pressure.movieinfo.adapter.MoviesAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.pressure.movieinfo.model.Movie;

import com.pressure.movieinfo.viewmodel.MainViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private PagedList<Movie>moviesList;
    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private MoviesAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        swipeRefreshLayout = findViewById(R.id.swipelayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMovies();
            }
        });
        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        getMovies();

        recyclerView = findViewById(R.id.rcview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);

    }
    void getMovies()
    {
        viewModel.getMovies().observe(MainActivity.this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movies) {
                moviesList = movies;
                loadList();
            }
        });
    }
    void loadList()
    {
        adapter.submitList(moviesList);

    }
}

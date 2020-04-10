package com.pressure.movieinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.pressure.movieinfo.R;
import com.pressure.movieinfo.activities.MovieActivity;
import com.pressure.movieinfo.databinding.RcviewLayoutBinding;
import com.pressure.movieinfo.model.Movie;
import java.util.List;

public class MoviesAdapter extends PagedListAdapter<Movie,MoviesAdapter.ViewHolder> {

    private Context context;
    public MoviesAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;

    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        RcviewLayoutBinding rcviewLayoutBinding;
        public ViewHolder(@NonNull RcviewLayoutBinding itemView) {
            super(itemView.getRoot());
               rcviewLayoutBinding = itemView;
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        Intent intent = new Intent(context, MovieActivity.class);
                        intent.putExtra("movie",getItem(pos));
                        context.startActivity(intent);
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RcviewLayoutBinding rcviewLayoutBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                , R.layout.rcview_layout,parent,false);

        return new ViewHolder(rcviewLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.rcviewLayoutBinding.setMovie(movie);


    }

}

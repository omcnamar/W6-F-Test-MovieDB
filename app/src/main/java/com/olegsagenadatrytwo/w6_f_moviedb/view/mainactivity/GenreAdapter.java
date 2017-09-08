package com.olegsagenadatrytwo.w6_f_moviedb.view.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.w6_f_moviedb.R;
import com.olegsagenadatrytwo.w6_f_moviedb.model.Genre;
import com.olegsagenadatrytwo.w6_f_moviedb.model.Result;
import com.olegsagenadatrytwo.w6_f_moviedb.view.detailmovieactivity.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omcna on 9/8/2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private List<Genre> list = new ArrayList<>();
    private Context context;
    private MainActivityPresenter presenter;

    public GenreAdapter(List<Genre> list, Context context, MainActivityPresenter presenter) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_genre_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvGenreName.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.downloadMovieData(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvGenreName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvGenreName = (TextView) itemView.findViewById(R.id.tvGenreName);
        }
    }
}

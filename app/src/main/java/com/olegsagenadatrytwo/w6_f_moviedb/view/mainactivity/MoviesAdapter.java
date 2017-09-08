package com.olegsagenadatrytwo.w6_f_moviedb.view.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.w6_f_moviedb.R;
import com.olegsagenadatrytwo.w6_f_moviedb.model.Result;
import com.olegsagenadatrytwo.w6_f_moviedb.view.detailmovieactivity.DetailMovieActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by omcna on 9/8/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Result> list = new ArrayList<>();
    private Context context;

    public MoviesAdapter(List<Result> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_movies_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(list.get(position).getTitle());
        final String imageURL = "https://image.tmdb.org/t/p/w500/" + list.get(position).getBackdropPath();
        Glide.with(context).load(imageURL).into(holder.ivImage);

        //onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra("movie", list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivImage;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}

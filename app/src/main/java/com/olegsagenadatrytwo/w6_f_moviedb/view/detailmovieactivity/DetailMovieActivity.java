package com.olegsagenadatrytwo.w6_f_moviedb.view.detailmovieactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.w6_f_moviedb.R;
import com.olegsagenadatrytwo.w6_f_moviedb.model.Result;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String TAG = "DetailMovieActivity";

    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvPopularity;
    private TextView tvOriginalLanguage;
    private TextView tvAdult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        ivImage = (ImageView)findViewById(R.id.ivDetailImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvOriginalLanguage = (TextView) findViewById(R.id.tvOriginalLanguage);
        tvAdult = (TextView) findViewById(R.id.tvAdult);

        Intent intent = getIntent();
        Result movie= (Result) intent.getSerializableExtra("movie");

        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvPopularity.setText(String.valueOf(movie.getPopularity()));
        tvOriginalLanguage.setText(String.valueOf(movie.getOriginalLanguage()));
        tvAdult.setText(String.valueOf(movie.getAdult()));

        final String imageURL = "https://image.tmdb.org/t/p/w500/" + movie.getBackdropPath();
        Glide.with(this).load(imageURL).into(ivImage);

    }
}

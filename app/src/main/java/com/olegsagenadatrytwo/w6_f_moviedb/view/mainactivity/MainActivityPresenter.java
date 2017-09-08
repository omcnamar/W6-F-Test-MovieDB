package com.olegsagenadatrytwo.w6_f_moviedb.view.mainactivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieData;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieGenre;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by omcna on 9/8/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{

    //https://api.themoviedb.org/3/genre/28/movies?api_key=1fcb900d38152c049c00c96a2c252d95&language=en-US&include_adult=false&sort_by=created_at.asc
    private MainActivityContract.View view;
    private Context context;
    private static final String KEY = "1fcb900d38152c049c00c96a2c252d95";
    private static final String BASE_URL = "api.themoviedb.org";
    public static final String TAG = "MainActivityPresenter";
    private MovieData movieData;
    private MovieGenre movieGenre;


    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void downloadMovieData(Integer id) {

        if(id == 0){
            id = 28;
        }

        Log.d(TAG, "downloadWeatherData: ");
        //make request to get the l
        final OkHttpClient okHttpClient;
        final Request request;
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(BASE_URL)
                .addPathSegment("3")
                .addPathSegment("genre")
                .addPathSegment(id + "")
                .addPathSegment("movies")
                .addQueryParameter("api_key", KEY)
                .addQueryParameter("language", "en-US")
                .addQueryParameter("include_adult", "false")
                .addQueryParameter("sort_by", "created_at.asc")
                .build();

        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "Failed to make connection", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                //Log.d(TAG, "onResponse: " + response.body().string());
                //Log.d(TAG, "onResponse: " + response.body().string());
                movieData = gson.fromJson(response.body().string(), MovieData.class);
                view.movieDataDownloadedUpdateUI(movieData);
            }
        });

    }

    @Override
    public void downloadGenres() {
        //https://api.themoviedb.org/3/genre/movie/list?api_key=1fcb900d38152c049c00c96a2c252d95&language=en-US
        Log.d(TAG, "downloadWeatherData: ");
        //make request to get the l
        final OkHttpClient okHttpClient;
        final Request request;
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(BASE_URL)
                .addPathSegment("3")
                .addPathSegment("genre")
                .addPathSegment("movie")
                .addPathSegment("list")
                .addQueryParameter("api_key", KEY)
                .addQueryParameter("language", "en-US")
                .build();

        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "Failed to make connection", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                //Log.d(TAG, "onResponse: " + response.body().string());
                //Log.d(TAG, "onResponse: " + response.body().string());
                movieGenre = gson.fromJson(response.body().string(), MovieGenre.class);
                view.genreDownloadedUpdateUI(movieGenre);
            }
        });

    }
}

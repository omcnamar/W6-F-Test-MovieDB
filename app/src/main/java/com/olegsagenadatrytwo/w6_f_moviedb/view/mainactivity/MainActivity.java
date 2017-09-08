package com.olegsagenadatrytwo.w6_f_moviedb.view.mainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.olegsagenadatrytwo.w6_f_moviedb.R;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieData;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieGenre;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{


    private MainActivityPresenter presenter;

    //Recycler view movie stuff
    private RecyclerView rvMovies;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private MoviesAdapter adapter;

    //Recycler view fenre stuff
    private RecyclerView rvGenre;
    private RecyclerView.LayoutManager layoutManager2;
    private RecyclerView.ItemAnimator itemAnimator2;
    private GenreAdapter adapterGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize recycler vies movie stuff
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setItemAnimator(itemAnimator);

        //initialize recycler vies genre stuff
        rvGenre = (RecyclerView) findViewById(R.id.rvGenre);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        itemAnimator2 = new DefaultItemAnimator();
        rvGenre.setLayoutManager(layoutManager2);
        rvGenre.setItemAnimator(itemAnimator2);

        //initialize presenter stuff
        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        presenter.setContext(this);
        presenter.downloadMovieData(0);
        presenter.downloadGenres();

    }

    @Override
    public void movieDataDownloadedUpdateUI(final MovieData movieData) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new MoviesAdapter(movieData.getResults(), getApplicationContext());
                rvMovies.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void genreDownloadedUpdateUI(final MovieGenre movieGenre) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterGenre = new GenreAdapter(movieGenre.getGenres(), getApplicationContext(), presenter);
                rvGenre.setAdapter(adapterGenre);
                adapterGenre.notifyDataSetChanged();
            }
        });
    }
}

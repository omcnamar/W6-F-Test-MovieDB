package com.olegsagenadatrytwo.w6_f_moviedb.view.mainactivity;

import com.olegsagenadatrytwo.w6_f_moviedb.BasePresenter;
import com.olegsagenadatrytwo.w6_f_moviedb.BaseView;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieData;
import com.olegsagenadatrytwo.w6_f_moviedb.model.MovieGenre;

/**
 * Created by omcna on 9/8/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView {
        void movieDataDownloadedUpdateUI(MovieData movieData);
        void genreDownloadedUpdateUI(MovieGenre movieGenre);
    }

    interface Presenter extends BasePresenter<View> {
        void downloadMovieData(Integer id);
        void downloadGenres();
    }

}

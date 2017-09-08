package com.olegsagenadatrytwo.w6_f_moviedb;

import android.content.Context;

/**
 * Created by omcna on 9/8/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void removeView();
    void setContext(Context context);
}

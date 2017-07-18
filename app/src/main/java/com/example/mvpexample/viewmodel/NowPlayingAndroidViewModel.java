package com.example.mvpexample.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.mvpexample.model.MovieViewInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of using the Android {@link ViewModel} to save data for {@link com.example.mvpexample.viewcontroller.NowPlayingActivity}
 *
 * Note - don't let the class name fool you. Just using this to store data during rotation
 * and NOT implementing MVVM. So no fetcher here...
 */
public class NowPlayingAndroidViewModel extends ViewModel {
    private List<MovieViewInfo> movieViewInfoList = new ArrayList<>();

    public List<MovieViewInfo> getMovieViewInfoList() {
        return this.movieViewInfoList;
    }
}

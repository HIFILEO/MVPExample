package com.example.mvpexample.dagger;

import android.app.Activity;

import com.example.mvpexample.viewcontroller.BaseActivity;
import com.example.mvpexample.viewcontroller.MovieDetailsActivity;
import com.example.mvpexample.viewcontroller.NowPlayingActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Module for building the subcomponents for various Dagger-injected Activities in the app.
 */
@Module(subcomponents = {
        BaseActivitySubComponent.class,
        NowPlayingActivitySubComponent.class,
        MovieDetailsActivitySubComponent.class
    }
)
public abstract class ActivitySubComponentBuilderModule {

    @Binds
    @IntoMap
    @ActivityKey(BaseActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindBaseActivityInjectorFactory(
            BaseActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(NowPlayingActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindNowPlayingInjectorFactory(
            NowPlayingActivitySubComponent.Builder builder);

    @Binds
    @IntoMap
    @ActivityKey(MovieDetailsActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMovieDetailsActivityFactory(
            MovieDetailsActivitySubComponent.Builder builder);
}

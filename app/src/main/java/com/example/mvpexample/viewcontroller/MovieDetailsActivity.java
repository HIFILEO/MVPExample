package com.example.mvpexample.viewcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mvpexample.R;

import timber.log.Timber;

public class MovieDetailsActivity extends BaseActivity {

    /**
     * Start this activity.
     * @param context - calling context.
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Timber.i("***** onCreate(NEW) "  +
                    "*****");
        } else {
            Timber.i("***** onCreate(FROM SAVED STATE " +
                    "*****");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }

    @Override
    public void onStart() {
        Timber.i("***** onStart() *****");
        super.onStart();
    }

    @Override
    public void onResume() {
        Timber.i("***** onResume() *****");
        super.onResume();
    }

    @Override
    public void onPause() {
        Timber.i("***** onPause() *****");
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Timber.i("***** onSaveInstanceState() *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        Timber.i("***** onStop() *****");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.i("***** onDestroy() *****");
    }

    @Override
    public void finish() {
        super.finish();
        Timber.i("***** finish() *****");
    }
}

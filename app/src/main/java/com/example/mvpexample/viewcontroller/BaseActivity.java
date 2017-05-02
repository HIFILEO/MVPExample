/*
Copyright 2017 LEO LLC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.example.mvpexample.viewcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mvpexample.dagger.HasInjectionProcessor;
import com.example.mvpexample.dagger.InjectionProcessor;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Base class for all {@link AppCompatActivity}.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDaggerMembers();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        ButterKnife.bind(this);
    }

    /**
     * Injects members into activity.
     */
    public void injectDaggerMembers() {
        AndroidInjection.inject(this);

        //Process Injections before activity start.
        if ((getApplication() instanceof HasInjectionProcessor)) {
            InjectionProcessor injectionProcessor =
                    ((HasInjectionProcessor) getApplication()).injectionProcessor();
            if (injectionProcessor != null) {
                injectionProcessor.processInjection(this);
            }
        }
    }

}

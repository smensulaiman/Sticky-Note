package com.acjl.foodmenu;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class StickyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
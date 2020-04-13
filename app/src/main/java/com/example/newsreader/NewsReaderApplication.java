package com.example.newsreader;

import com.example.newsreader.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.realm.Realm;

public class NewsReaderApplication extends DaggerApplication {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        // Initialize Realm.
        Realm.init( this );
    }
    
    /**
     * Return an AndroidInjector for the concrete DaggerApplication.
     */
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create( this );
    }
    
}

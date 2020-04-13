package com.example.newsreader.view.main;

import com.example.newsreader.data.MainMvpModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {
    
    @Provides
    static MainMvpPresenter provideMainPresenter( MainMvpView mainMvpView, MainMvpModel databaseMvpModel ) {
        return new MainPresenter( mainMvpView, databaseMvpModel );
    }
    
    @Binds
    abstract MainMvpView bindMainActivity( MainActivity mainActivity );
    
}

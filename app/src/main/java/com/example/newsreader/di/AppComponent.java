package com.example.newsreader.di;

import com.example.newsreader.NewsReaderApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component( modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
} )
public interface AppComponent extends AndroidInjector<NewsReaderApplication> {
    
    @Component.Factory
    abstract class Builder implements Factory<NewsReaderApplication> {}
    
}

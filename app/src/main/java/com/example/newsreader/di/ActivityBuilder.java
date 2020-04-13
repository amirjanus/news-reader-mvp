package com.example.newsreader.di;

import com.example.newsreader.view.article.adapter.PagerArticleFragment;
import com.example.newsreader.view.main.MainActivity;
import com.example.newsreader.view.main.MainActivityModule;
import com.example.newsreader.view.article.ArticleActivity;
import com.example.newsreader.view.article.ArticleActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    
    @ContributesAndroidInjector( modules = MainActivityModule.class )
    abstract MainActivity contributeMainActivity();
    
    @ContributesAndroidInjector( modules = ArticleActivityModule.class )
    abstract ArticleActivity contributeNewsActivity();
    
    @ContributesAndroidInjector()
    abstract PagerArticleFragment contributePagerArticleFragment();
    
}

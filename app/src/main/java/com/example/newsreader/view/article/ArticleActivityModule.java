package com.example.newsreader.view.article;

import com.example.newsreader.data.MainMvpModel;
import com.example.newsreader.view.article.adapter.PagerArticleAdapter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ArticleActivityModule {
    
    @Provides
    static PagerArticleAdapter providePagerArticleAdapter( ArticleActivity articleActivity ) {
        return new PagerArticleAdapter( articleActivity );
    }
    
    @Provides
    static ArticleMvpPresenter provideNewsPresenter( ArticleMvpView articleMvpView, MainMvpModel databaseMvpModel ) {
        return new ArticlePresenter( articleMvpView, databaseMvpModel );
    }
    
    @Binds
    abstract ArticleMvpView bindArticleActivity( ArticleActivity articleActivity );
    
}

package com.example.newsreader.data;

import android.annotation.SuppressLint;

import com.example.newsreader.data.local.interfaces.NewsLocalDb;
import com.example.newsreader.data.remote.newsapi.NewsApi;
import com.example.newsreader.utils.models.Article;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel implements MainMvpModel {
    
    private NewsLocalDb mLocalDb;
    private NewsApi mApi;
    
    @Inject
    public MainModel( NewsLocalDb newsLocalDb, NewsApi newsApi ) {
        mLocalDb = newsLocalDb;
        mApi = newsApi;
    }
    
    /**
     * Returns list of articles from local database.
     *
     * @param newsSourceId Articles's parent object ID.
     * @return Single with a list of articles or empty list if none exist that satisfy search query.
     */
    @Override
    public Single<List<Article>> getLocalArticles( String newsSourceId ) {
        return Single.fromCallable( () -> mLocalDb.getArticles( newsSourceId ) )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }
    
    /**
     * Returns list of articles from local database.
     *
     * @param newsSourceId Articles's parent object ID.
     * @param date       Searches for articles newer than this Date.
     * @return Single with a list of articles or empty list if none exist that satisfy search query.
     */
    @SuppressLint( "CheckResult" )
    @Override
    public Single<List<Article>> getLocalArticles( String newsSourceId, Date date ) {
        return Single.fromCallable( () -> mLocalDb.getArticles( newsSourceId, date ) )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }
    
    /**
     * Returns list of articles from remote api.
     *
     * @param newsSourceId News source ID.
     * @param sortBy     Indicates how to sort articles.
     * @return Single with a list of articles.
     */
    @Override
    public Single<List<Article>> getRemoteArticles( String newsSourceId, String sortBy ) {
        return mApi.getNews( newsSourceId, sortBy )
                .subscribeOn( Schedulers.io() )
                .observeOn( Schedulers.io() )
                .map( newsSource -> {
                    mLocalDb.insertNews( newsSource );
                    
                    return 0;
                } )
                .map( integer -> mLocalDb.getArticles( newsSourceId ) )
                .observeOn( AndroidSchedulers.mainThread() );
    }
    
}

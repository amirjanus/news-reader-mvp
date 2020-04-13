package com.example.newsreader.data.local.realm;

import com.example.newsreader.data.local.interfaces.NewsLocalDb;
import com.example.newsreader.data.local.realm.models.RmNewsSource;
import com.example.newsreader.utils.models.Article;
import com.example.newsreader.utils.models.NewsSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Realm's local database implementation.
 */
public class RealmNewsLocalDb implements NewsLocalDb {
    
    private RealmConfiguration mConfig;
    private Realm mRealm;
    
    public RealmNewsLocalDb( RealmConfiguration config ) {
        mConfig = config;
    }
    
    /**
     * Get articles from database.
     *
     * @param newsSourceId NewsSourceNames ID.
     * @param date         Articles are returned if the are newer than passed Date.
     */
    public List<Article> getArticles( String newsSourceId, Date date ) {
        mRealm = Realm.getInstance( mConfig );
        
        RmNewsSource rmNewsSource = mRealm.where( RmNewsSource.class )
                .equalTo( "id", newsSourceId )
                .greaterThanOrEqualTo( "date", date )
                .findFirst();
        
        List<Article> articleList = new ArrayList<>( 0 );
        
        if ( rmNewsSource != null )
            articleList = rmNewsSource.getArticleList();
        
        mRealm.close();
        
        return articleList;
    }
    
    /**
     * Get articles from database.
     *
     * @param newsSourceId Articles's parent object ID.
     */
    public List<Article> getArticles( String newsSourceId ) {
        mRealm = Realm.getInstance( mConfig );
        
        RmNewsSource rmNewsSource = mRealm.where( RmNewsSource.class )
                .equalTo( "id", newsSourceId )
                .findFirst();
        
        List<Article> articleList = new ArrayList<>( 0 );
        
        if ( rmNewsSource != null )
            articleList = rmNewsSource.getArticleList();
        
        mRealm.close();
        
        return articleList;
    }
    
    /**
     * Inserts articles in database ( or updates if it already exist ).
     *
     * @param newsSource NewsSourceNames data to save in database.
     */
    public void insertNews( NewsSource newsSource ) {
        RmNewsSource rmNewsSource = new RmNewsSource( newsSource );
        
        mRealm = Realm.getInstance( mConfig );
        
        mRealm.executeTransaction( realm -> realm.insertOrUpdate( rmNewsSource ) );
        
        mRealm.close();
    }
    
}

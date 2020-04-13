package com.example.newsreader.data.local.interfaces;

import com.example.newsreader.utils.models.Article;
import com.example.newsreader.utils.models.NewsSource;

import java.util.Date;
import java.util.List;

/**
 * interface for local database ( Room, Realm ... ).
 */
public interface NewsLocalDb {
    
    /**
     * Get articles from database.
     *
     * @param newsSourceId NewsSourceNames ID.
     * @param date         Articles are returned if the are newer than passed Date.
     */
    List<Article> getArticles( String newsSourceId, Date date );
    
    /**
     * Get articles from database.
     *
     * @param newsSourceId Articles's parent object ID.
     */
    List<Article> getArticles( String newsSourceId );
    
    /**
     * Inserts articles in database ( or updates if it already exist ).
     *
     * @param newsSource NewsSourceNames data to save in database.
     */
    void insertNews( NewsSource newsSource );
    
}

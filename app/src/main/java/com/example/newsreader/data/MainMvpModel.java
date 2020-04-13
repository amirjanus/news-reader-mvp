package com.example.newsreader.data;

import com.example.newsreader.utils.models.Article;

import java.util.Date;
import java.util.List;

import io.reactivex.Single;

public interface MainMvpModel {
    
    /**
     * Returns list of articles from local database.
     *
     * @param newsSourceId Articles's parent object ID.
     * @return Single with a list of articles or empty list if none exist that satisfy search query.
     */
    Single<List<Article>> getLocalArticles( String newsSourceId );
    
    /**
     * Returns list of articles from local database.
     *
     * @param newsSourceId Articles's parent object ID.
     * @param date       Searches for articles newer than this Date.
     * @return Single with a list of articles or empty list if none exist that satisfy search query.
     */
    Single<List<Article>> getLocalArticles( String newsSourceId, Date date );
    
    /**
     * Returns list of articles from remote api.
     *
     * @param newsSourceId News source ID.
     * @param sortBy     Indicates how to sort articles.
     * @return Single with a list of articles.
     */
    Single<List<Article>> getRemoteArticles( String newsSourceId, String sortBy );
    
}

package com.example.newsreader.data.local.realm.models;

import com.example.newsreader.utils.models.Article;
import com.example.newsreader.utils.models.NewsSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Class for saving news data to Realm database.
 */
public class RmNewsSource extends RealmObject {
    
    @PrimaryKey
    public String id;
    
    /* Timestamp showing when was http response received. */
    public Date date;
    
    /* List of articles. */
    public RealmList<RmArticle> articles;
    
    public RmNewsSource() {}
    
    public RmNewsSource( NewsSource newsSource ) {
        id = newsSource.id;
        date = newsSource.date;
        
        articles = new RealmList<>();
        
        for ( Article article : newsSource.articles )
            articles.add( new RmArticle( article ) );
    }
    
    /**
     * Returns Articles list.
     *
     * @return Articles list.
     */
    public List<Article> getArticleList() {
        List<Article> result = new ArrayList<>();
        
        for ( RmArticle rmArticle : articles )
            result.add( rmArticle.toArticle() );
        
        return result;
    }
    
}

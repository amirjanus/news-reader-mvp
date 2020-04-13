package com.example.newsreader.data.local.realm.models;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.example.newsreader.utils.models.Article;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Class for saving news data to Realm database.
 */
public class RmArticle extends RealmObject {
    
    @PrimaryKey
    public long id;
    
    /* Article's title. */
    public String title;
    
    /* Article's text. */
    public String description;
    
    /* Url to article's image. */
    public String urlToImage;
    
    public RmArticle() {}
    
    public RmArticle( Article article ) {
        id = UUID.randomUUID().getLeastSignificantBits();
        title = article.title;
        description = article.description;
        urlToImage = article.urlToImage;
    }
    
    /**
     * Create new Articles object from this object.
     *
     * @return Article object created from this object.
     */
    public Article toArticle() {
        return new Article( id, title, description, urlToImage );
    }
    
    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @SuppressLint( "DefaultLocale" )
    @NonNull
    @Override
    public String toString() {
        return String.format( "ID: %1$d, Title: %2$s", id, title );
    }
}

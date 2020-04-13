package com.example.newsreader.utils.models;

import java.util.Date;
import java.util.List;

/**
 * Class used by RealmNewsApi class for holding data from NewsApi.
 */
public class NewsSource {

    public String id;

    /* Timestamp showing when was http response received. */
    public Date date;
    
    /* List of articles. */
    public List<Article> articles;

}

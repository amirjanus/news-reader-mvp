package com.example.newsreader.data.local.realm;

import com.example.newsreader.data.local.realm.models.RmArticle;
import com.example.newsreader.data.local.realm.models.RmNewsSource;

import io.realm.annotations.RealmModule;

@RealmModule( classes = {
        RmNewsSource.class,
        RmArticle.class
} )
public class RmDatabaseModule {}

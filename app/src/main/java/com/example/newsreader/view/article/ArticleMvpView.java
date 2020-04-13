package com.example.newsreader.view.article;

import com.example.newsreader.utils.models.Article;

import java.util.List;

public interface ArticleMvpView {
    
    /**
     * Display Articles list in view.
     *
     * @param articleList List of Articles to show.
     * @param index       Position index of Article to show as current item.
     */
    void showArticles( List<Article> articleList, int index );
    
    /**
     * Show error message to user.
     */
    void showErrorMessage();
    
    /**
     * Display title in app's toolbar.
     *
     * @param title String to show in toolbar.
     */
    void showToolbarTitle( String title );
    
}
